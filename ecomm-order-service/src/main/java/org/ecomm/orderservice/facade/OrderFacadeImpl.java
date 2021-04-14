package org.ecomm.orderservice.facade;

import org.ecomm.foundation.api.AppLogger;
import org.ecomm.foundation.model.*;
import org.ecomm.foundation.repo.OrderHistoryRepository;
import org.ecomm.foundation.repo.OrderItemRepository;
import org.ecomm.foundation.repo.OrderPaymentRepository;
import org.ecomm.foundation.repo.OrderRepository;
import org.ecomm.orderservice.util.AppRestHelper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.annotation.RequestScope;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static javax.transaction.Transactional.TxType;

@Named
public class OrderFacadeImpl implements OrderFacade{


    @AppLogger
    Logger logger;

    final OrderRepository orderRepository;
    final OrderItemRepository orderItemRepository;
    final OrderHistoryRepository orderHistoryRepository;
    final OrderPaymentRepository orderPaymentRepository;
    final AppRestHelper restHelper;
    private final OrderValidator orderValidator;

    @Value("${app.paymentService.processPayment.url}")
    String urlForProcessPayment;


    @Inject
    public OrderFacadeImpl(OrderRepository orderRepository,
                           OrderItemRepository orderItemRepository,
                           OrderHistoryRepository orderHistoryRepository,
                           OrderPaymentRepository orderPaymentRepository,
                           OrderValidator orderValidator,
                           AppRestHelper restHelper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderValidator = orderValidator;
        this.restHelper = restHelper;
        this.orderHistoryRepository = orderHistoryRepository;
        this.orderPaymentRepository = orderPaymentRepository;
    }

    @Override
    @Transactional(TxType.SUPPORTS)
    public Order validateOrder(Order preOrder) throws Exception {
        Order validOrder = new Order();
        //First Validate the customer is valid
        Customer validCustomer = orderValidator.validateCustomer(preOrder);
        validOrder.setCustomer(validCustomer);
        logger.info("Customer validated successfully.");

        validOrder.setShippingAddress(preOrder.getShippingAddress());
        Address shippingAddress = orderValidator.validateAddress(validOrder);
        validOrder.setShippingAddress(shippingAddress);
        logger.info("Address validated successfully");

        //Now Validate Items
        List<OrderItem> validItems = orderValidator.validateItems(preOrder);
        logger.info("Items retrieved successfully");
        if(orderValidator.validateOrderQuantity(validItems)){
            validOrder.setItems(validItems);
            logger.info("Order quantity validated");
        }

        validOrder.setTotalAmt(orderValidator.calcOrderTotal(validOrder));

        //Now validate the payment
        List<OrderPayment> validPayments = orderValidator.validatePayments(preOrder);
        logger.info("Successfully retrieved payments");
        validOrder.setPayments(validPayments);

        return validOrder;
    }

    @Override
    @Transactional(TxType.REQUIRES_NEW)
    public boolean processPayment(Order savedOrder) {
        //Now try and process all the payments.
        for(OrderPayment orderPayment: savedOrder.getPayments()) {
            try{
                orderPayment.setPaymentType("CUST_PAYMENT");
                orderPayment.setOrder(savedOrder);
                orderPayment.setRequestTime(new Timestamp(System.currentTimeMillis()));
                orderPayment.setPaymentCode(UUID.randomUUID().toString());

                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("action", "PROCESS_PAYMENT");
                paramMap.put("amount", orderPayment.getAmount());
                paramMap.put("payment", orderPayment.getPayment());

                ResponseEntity<PaymentHistory> paymentResponse =
                  restHelper.postEntityFromService(urlForProcessPayment, PaymentHistory.class, paramMap);
                if(paymentResponse.getStatusCode() == HttpStatus.OK){
                    PaymentHistory history = paymentResponse.getBody();
                    orderPayment.setStatus(history.getStatus());
                    orderPayment.setResponseCode(System.currentTimeMillis() + "");
                    orderPayment.setResponseTime(new Timestamp(System.currentTimeMillis()));
                    orderPaymentRepository.save(orderPayment);
                    if (!history.getStatus()) {
                        //TODO: Failed payment. Process to add payment to return payment queue
                        //failed payment
                        //add to

                        return false;
                    }
                    logger.info("payment successful");
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
                return false;

            }
        }
        return true;
    }

    @Transactional(TxType.SUPPORTS)
    Order createPendingOrder(Order detatched) {
        detatched.setStatus("PENDING_PAYMENT");
        Order savedOrder = orderRepository.save(detatched);
        logger.info("Order saved successfully. Order id: {}", savedOrder.getId());

        for (OrderItem orderItem: savedOrder.getItems()) {
            orderItem.setOrder(savedOrder);
            orderItemRepository.save(orderItem);
            OrderHistory history = new OrderHistory()
              .setOrderItem(orderItem)
              .setStatus(true)
              .setAction("PENDING_PAYMENT")
              .setEntryTime(new Timestamp(System.currentTimeMillis()))
              .setUserType("REGISTERED")
              .setNotes("Log for item " + orderItem.getItem().getCode());
            orderHistoryRepository.save(history);
        }

        return savedOrder;
    }

    @Override
    @Transactional(TxType.REQUIRES_NEW)
    public Order createOrder(Order preOrder) throws Exception {
        logger.info("Starting new order request. going to validate order");
        Order validOrder = this.validateOrder(preOrder);

        //upto this point all the data verified.
        //Create a pending order
        validOrder.setStatus("PENDING_PAYMENT");
        Order savedOrder = this.createPendingOrder(validOrder);

        //Now try and process all the payments.
        if(this.processPayment(savedOrder)) {
            logger.info("Successfully processed payments for order: {}", savedOrder.getId());
            Order updatedOrder = updateOrderToPaymentComplete(savedOrder);
            logger.info("Order status updated to Complete.");
            return updatedOrder;
        }

        return savedOrder;
    }

    @Transactional(TxType.SUPPORTS)
    Order updateOrderToPaymentComplete(Order savedOrder) {
        savedOrder.setStatus("PAYMENT_COMPLETE");
        for (OrderItem orderItem: savedOrder.getItems()) {
            OrderHistory history = new OrderHistory()
              .setOrderItem(orderItem)
              .setStatus(true)
              .setAction("PAYMENT_COMPLETE")
              .setEntryTime(new Timestamp(System.currentTimeMillis()))
              .setUserType("REGISTERED")
              .setNotes("Log for item " + orderItem.getItem().getCode());
            orderHistoryRepository.save(history);
        }
        Order updatedOrder = orderRepository.save(savedOrder);
        return updatedOrder;
    }
}

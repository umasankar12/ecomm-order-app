package org.ecomm.orderservice.facade;

import org.ecomm.foundation.api.AppLogger;
import org.ecomm.foundation.model.*;
import org.ecomm.foundation.repo.OrderHistoryRepository;
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
import java.util.List;

import static javax.transaction.Transactional.TxType;

@Named
public class OrderFacadeImpl implements OrderFacade{


    @AppLogger
    Logger logger;

    final OrderRepository orderRepository;
    final OrderHistoryRepository orderHistoryRepository;
    final AppRestHelper restHelper;
    private final OrderValidator orderValidator;

    @Value("${app.paymentService.processPayment.url}")
    String urlForProcessPayment;


    @Inject
    public OrderFacadeImpl(OrderRepository orderRepository,
                           OrderHistoryRepository orderHistoryRepository,
                           OrderValidator orderValidator,
                           AppRestHelper restHelper) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
        this.restHelper = restHelper;
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Override
    @Transactional(TxType.SUPPORTS)
    public Order validateOrder(Order preOrder) throws Exception {
        Order validOrder = new Order();
        //First Validate the customer is valid
        Customer validCustomer = orderValidator.validateCustomer(preOrder);
        validOrder.setCustomer(validCustomer);
        logger.info("Customer validated successfully.");

        //Now Validate Items
        List<OrderItem> validItems = orderValidator.validateItems(preOrder);
        logger.info("Items retrieved successfully");
        if(orderValidator.validateOrderQuantity(validItems)){
//            validOrder.setItems(validItems);
            logger.info("Order quantity validated");
        }

        //Now validate the payment
        List<OrderPayment> validPayments = orderValidator.validatePayments(preOrder);
        logger.info("Successfully retrieved payments");
//        validOrder.setPayments(validPayments);

        return validOrder;
    }

    @Override
    @Transactional(TxType.REQUIRES_NEW)
    public boolean processPayment(Order savedOrder) {
        //Now try and process all the payments.
//        for(OrderPayment orderPayment: savedOrder.getPayments()) {
//            try{
//                ResponseEntity<PaymentHistory> paymentResponse =
//                  restHelper.postEntityFromService(urlForProcessPayment, PaymentHistory.class, null);
//                if(paymentResponse.getStatusCode() == HttpStatus.OK){
//                    PaymentHistory history = paymentResponse.getBody();
//                    if (!history.getStatus()) {
//                        //TODO: Failed payment. Process to add payment to return payment queue
//                        //failed payment
//                        //add to
//                        return false;
//                    }
//                    logger.info("payment successful");
//                }
//            }
//            catch (Exception ex){
//                ex.printStackTrace();
//                return false;
//
//            }
//        }
        return true;
    }

    @Transactional(TxType.SUPPORTS)
    Order createPendingOrder(Order detatched) {
        detatched.setStatus("PENDING_PAYMENT");
        Order savedOrder = orderRepository.save(detatched);
        logger.info("Order saved successfully. Order id: {}", savedOrder.getId());

//        for (OrderItem orderItem: savedOrder.getItems()) {
//            OrderHistory history = new OrderHistory()
//              .setOrderItem(orderItem)
//              .setStatus(true)
//              .setAction("PENDING_PAYMENT")
//              .setEntryTime(new Timestamp(System.currentTimeMillis()))
//              .setUserType("REGISTERED")
//              .setNotes("Log for item " + orderItem.getItem().getCode());
//            orderHistoryRepository.save(history);
//        }

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
//        for (OrderItem orderItem: savedOrder.getItems()) {
//            OrderHistory history = new OrderHistory()
//              .setOrderItem(orderItem)
//              .setStatus(true)
//              .setAction("PAYMENT_COMPLETE")
//              .setEntryTime(new Timestamp(System.currentTimeMillis()))
//              .setUserType("REGISTERED")
//              .setNotes("Log for item " + orderItem.getItem().getCode());
//            orderHistoryRepository.save(history);
//        }
        Order updatedOrder = orderRepository.save(savedOrder);
        return updatedOrder;
    }
}

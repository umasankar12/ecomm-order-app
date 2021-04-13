package org.ecomm.orderservice.facade;

import org.ecomm.foundation.api.AppLogger;
import org.ecomm.foundation.model.*;
import org.ecomm.orderservice.util.AppRestHelper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.annotation.RequestScope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScope
public class OrderValidatorImpl implements OrderValidator {

    @AppLogger
    Logger logger;

    private final AppRestHelper restHelper;

    @Value("${app.customerService.getUser.url}")
    String urlForUser;


    @Value("${app.itemService.getItem.url}")
    String urlForItem;

    @Value("${app.paymentService.getItem.url}")
    String urlForPayment;

    @Inject
    OrderValidatorImpl(AppRestHelper restHelper) {
        this.restHelper = restHelper;
    }

    @Override
    public List<OrderItem> validateItems(Order preOrder) throws Exception {
        List<OrderItem> validItems = new ArrayList<>();
//        for (OrderItem orderItem : preOrder.getItems()) {
//            Item invItem = restHelper.getEntityFromService(urlForItem + "/" + orderItem.getItem().getCode(), Item.class);
//            orderItem.setItem(invItem);
//            validItems.add(orderItem);
//        }

        return validItems;
    }

    @Override
    public List<OrderPayment> validatePayments(Order preOrder) throws Exception {
        List<OrderPayment> validPayments = new ArrayList<>();
//        for (OrderPayment orderPayment : preOrder.getPayments()) {
//            Integer paymentId = orderPayment.getPayment().getId();
//            Payment payment = restHelper.getEntityFromService(urlForPayment + "/" + paymentId, Payment.class);
//            orderPayment.setPayment(payment);
//            validPayments.add(orderPayment);
//        }
        return validPayments;
    }

    @Override
    public Address validateAddress(Order preOrder) {
        return null;
    }

    @Override
    public Customer validateCustomer(Order preOrder) throws Exception {
        Integer customerId = preOrder.getCustomer().getId();
        logger.info("Going to create new order for {}", customerId);
        Customer validCustomer = restHelper.getEntityFromService(urlForUser + "/" + customerId, Customer.class);
        if (validCustomer == null)
            throw new Exception("Invalid Customer Id received " + customerId);
        logger.info("Fetched valid customer");
        return validCustomer;
    }

    @Override
    public boolean validateOrderQuantity(List<OrderItem> orderItems) throws Exception {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getQuantity().doubleValue() > orderItem.getItem().getAvailQty().doubleValue()) {
                throw new Exception(String.format("Invalid Quantity requested for %s, available=%d, requested=%d",
                  orderItem.getItem().getName(),
                  orderItem.getItem().getAvailQty(),
                  orderItem.getQuantity()
                ));
            }
        }
        return true;
    }
}

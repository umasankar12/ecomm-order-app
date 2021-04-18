package org.ecomm.orderservice.facade;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.ecomm.foundation.api.AppLogger;
import org.ecomm.foundation.model.*;
import org.ecomm.orderservice.util.AppRestHelper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.annotation.RequestScope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Scope("prototype")
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

    @Value("${app.customerService.saveAddess.url}")
    String urlForAddress;

    @Inject
    OrderValidatorImpl(AppRestHelper restHelper) {
        this.restHelper = restHelper;
    }

    @Override
    public List<OrderItem> validateItems(Order preOrder) throws Exception {
        List<OrderItem> validItems = new ArrayList<>();
        List<Integer> ids = preOrder.getItems().stream()
                .map(orderItem -> orderItem.getItem().getId()).collect(Collectors.toList());
        for (OrderItem orderItem : preOrder.getItems()) {
            Item invItem = restHelper.getEntityFromService(urlForItem + "/item/" + orderItem.getItem().getCode(), Item.class);
            orderItem.setItem(invItem);
            validItems.add(orderItem);
        }
        ResponseEntity<List> items = restHelper.postEntityFromService(urlForItem+"/allItems",List.class ,ids);
        return validItems;
    }

    @Override
    public List<OrderPayment> validatePayments(Order preOrder) throws Exception {
        List<OrderPayment> validPayments = new ArrayList<>();
        for (OrderPayment orderPayment : preOrder.getPayments()) {
            Integer paymentId = orderPayment.getPayment().getId();
            Payment payment = restHelper.getEntityFromService(urlForPayment + "/" + paymentId, Payment.class);
            orderPayment.setPayment(payment);
            validPayments.add(orderPayment);
        }
        return validPayments;
    }

    private Address saveAddress(Address detached) {
        try{
            ResponseEntity<Address> addrResponse = restHelper.postEntityFromService(urlForAddress, Address.class, detached);
            return addrResponse.getBody();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Address validateAddress(Order preOrder) throws Exception {
        List<Address> customerAddresses = preOrder.getCustomer().getAddresses();
        Address shippingAddress = preOrder.getShippingAddress();

        Address filteredAddr = customerAddresses.stream()
          .filter(a -> a.getId() == shippingAddress.getId())
          .findFirst()
          .orElseGet(() -> saveAddress(shippingAddress));

        if(filteredAddr ==null)
            throw new Exception("Could Not save Temporary address");
        return filteredAddr;
    }

    @Override
    public Customer validateCustomer(Order preOrder) throws Exception {
        Integer customerId = preOrder.getInputCustomer().getId();
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

    @Override
    public Double calcOrderTotal(Order order) {
        Double total = 0.0;
        for (OrderItem orderItem : order.getItems()) {
            total = total + orderItem.getQuantity().doubleValue() * orderItem.getItem().getUnitPrice().doubleValue();
        }
        return total;
    }
}

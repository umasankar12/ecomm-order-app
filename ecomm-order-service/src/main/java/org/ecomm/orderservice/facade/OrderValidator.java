package org.ecomm.orderservice.facade;

import org.ecomm.foundation.model.*;

import java.util.List;

public interface OrderValidator {

    List<OrderItem> validateItems(Order preOrder) throws Exception;

    List<OrderPayment> validatePayments(Order preOrder) throws Exception;

    Address validateAddress(Order preOrder) throws Exception;

    Customer validateCustomer(Order preOrder) throws Exception;

    boolean validateOrderQuantity(List<OrderItem> orderItems) throws Exception;

}

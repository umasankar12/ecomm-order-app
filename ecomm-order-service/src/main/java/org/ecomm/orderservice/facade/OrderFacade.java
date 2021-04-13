package org.ecomm.orderservice.facade;

import org.ecomm.foundation.model.Order;

public interface OrderFacade {

    Order validateOrder(Order preOrder) throws Exception;

    boolean processPayment(Order preOrder);

    Order createOrder(Order preOrder) throws Exception;

}

package org.ecomm.orderservice.dto;

import lombok.*;
import org.ecomm.foundation.model.Address;
import org.ecomm.foundation.model.Customer;
import org.ecomm.foundation.model.Order;
import org.ecomm.foundation.model.Payment;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrderPayload {

    Order order;
    Customer customer;
    Address shippingAddress;
    List<Payment> payments;

}

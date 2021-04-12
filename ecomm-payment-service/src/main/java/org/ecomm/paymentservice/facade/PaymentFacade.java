package org.ecomm.paymentservice.facade;

import org.ecomm.paymentservice.model.PaymentRequest;
import org.springframework.data.util.Pair;



public interface PaymentFacade {

    Pair<Boolean, String> processPayment(PaymentRequest request);

}

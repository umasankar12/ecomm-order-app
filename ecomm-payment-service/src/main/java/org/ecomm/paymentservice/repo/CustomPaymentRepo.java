package org.ecomm.paymentservice.repo;

import org.ecomm.paymentservice.model.Payment;


public interface CustomPaymentRepo {

    Payment scrambleCard(Payment p);

    String validatePayment(Payment p);

}

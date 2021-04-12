package org.ecomm.paymentservice.facade;

import org.ecomm.foundation.model.PaymentHistory;
import org.ecomm.paymentservice.model.PaymentRequest;

public interface PaymentLogger {

    PaymentHistory logPaymentAction(PaymentRequest action);

}

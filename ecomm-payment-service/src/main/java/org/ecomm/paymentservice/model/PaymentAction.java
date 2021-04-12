package org.ecomm.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ecomm.foundation.model.Payment;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentAction {

    String source;
    String action;
    String actionDetail;
    String status;
    String reason;
    Timestamp entryTime;
    double amount;
    Payment payment;

}

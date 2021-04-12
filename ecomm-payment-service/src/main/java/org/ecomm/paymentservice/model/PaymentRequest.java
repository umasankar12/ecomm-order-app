package org.ecomm.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ecomm.foundation.model.Payment;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    String source;
    String action;
    String actionDetail;
    String status;
    String reason;
    Timestamp entryTime;
    double amount;
    Payment payment;

    public String getSource() {
        return source;
    }

    public PaymentRequest setSource(String source) {
        this.source = source;
        return this;
    }

    public String getAction() {
        return action;
    }

    public PaymentRequest setAction(String action) {
        this.action = action;
        return this;
    }

    public String getActionDetail() {
        return actionDetail;
    }

    public PaymentRequest setActionDetail(String actionDetail) {
        this.actionDetail = actionDetail;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public PaymentRequest setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public PaymentRequest setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public PaymentRequest setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentRequest setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Payment getPayment() {
        return payment;
    }

    public PaymentRequest setPayment(Payment payment) {
        this.payment = payment;
        return this;
    }
}

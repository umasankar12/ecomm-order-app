package org.ecomm.paymentservice.model;


import org.ecomm.foundation.model.Payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentResponse {

    //Response status
    int status;

    String message;

    Map<String, Object> data = new HashMap<>();

    public PaymentResponse() {
    }

    public PaymentResponse(int status, String message, Map<String, Object> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static PaymentResponse createSuccessResponse(Payment payment, String message) {
        PaymentResponse response = new PaymentResponse()
          .setStatus(200)
          .setMessage(message);
        response.getData().put("payment", payment);
        return response;
    }

    public static PaymentResponse createSuccessResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse()
          .setStatus(200)
          .setMessage("");
        response.getData().put("payment", payment);
        return response;
    }

    public static PaymentResponse createFailureResponse(Payment payment, int code, String message) {
        PaymentResponse response = new PaymentResponse()
          .setStatus(code)
          .setMessage(message);
        response.getData().put("payment", payment);
        return response;
    }

    public int getStatus() {
        return status;
    }

    public PaymentResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public PaymentResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public PaymentResponse setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }
}

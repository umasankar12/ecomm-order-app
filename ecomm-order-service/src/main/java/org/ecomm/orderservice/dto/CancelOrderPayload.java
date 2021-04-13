package org.ecomm.orderservice.dto;

public class CancelOrderPayload {
    private int orderId;
    private String notes;

    public int getOrderId() {
        return orderId;
    }

    public CancelOrderPayload setOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public CancelOrderPayload setNotes(String notes) {
        this.notes = notes;
        return this;
    }
}

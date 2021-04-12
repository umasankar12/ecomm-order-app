package org.ecomm.foundation.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "paymenthistory", schema = "customer")
public class PaymentHistory {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator="customer.paymenthistory_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer.paymenthistory_id_seq", sequenceName = "customer.paymenthistory_id_seq",
    allocationSize = 1, initialValue = 10)
    private int id;

    @Basic
    @Column(name = "request_id", nullable = true, length = 100)
    private String requestId;

    @Basic
    @Column(name = "action", nullable = true, length = 200)
    private String action;

    @Column(name = "action_detail", nullable = true, length = 200)
    private String actionDetail;

    @Column(name = "status", nullable = true, length = 1000)
    private Boolean status;

    @Column(name = "entry_time", nullable = true)
    private Timestamp entryTime;

    @Column(name = "entry_data")
    private String entryData;

    public int getId() {
        return id;
    }

    public PaymentHistory setId(int id) {
        this.id = id;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public PaymentHistory setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getAction() {
        return action;
    }

    public PaymentHistory setAction(String action) {
        this.action = action;
        return this;
    }

    public String getActionDetail() {
        return actionDetail;
    }

    public PaymentHistory setActionDetail(String actionDetail) {
        this.actionDetail = actionDetail;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public PaymentHistory setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public PaymentHistory setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
        return this;
    }

    public String getEntryData() {
        return entryData;
    }

    public PaymentHistory setEntryData(String entryData) {
        this.entryData = entryData;
        return this;
    }
}

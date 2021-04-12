package org.ecomm.paymentservice.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(schema = "customer")
public class PaymentLog {
    private int id;
    private String requestId;
    private Integer paymentId;
    private String source;
    private String action;
    private String actionDetail;
    private Boolean status;
    private Timestamp logTime;
    private String logData;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer.paymentlog_id_seq")
    @SequenceGenerator(name = "customer.paymentlog_id_seq", sequenceName = "customer.paymentlog_id_seq")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public PaymentLog setId(int id) {
        this.id = id;
        return this;
    }

    @Basic
    @Column(name = "request_id", nullable = true, length = 100)
    public String getRequestId() {
        return requestId;
    }

    public PaymentLog setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    @Basic
    @Column(name = "payment_id", nullable = true)
    public Integer getPaymentId() {
        return paymentId;
    }

    public PaymentLog setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    @Basic
    @Column(name = "source", nullable = true, length = 100)
    public String getSource() {
        return source;
    }

    public PaymentLog setSource(String source) {
        this.source = source;
        return this;
    }

    @Basic
    @Column(name = "action", nullable = true, length = 200)
    public String getAction() {
        return action;
    }

    public PaymentLog setAction(String action) {
        this.action = action;
        return this;
    }

    @Basic
    @Column(name = "action_detail", nullable = true, length = 1000)
    public String getActionDetail() {
        return actionDetail;
    }

    public PaymentLog setActionDetail(String actionDetail) {
        this.actionDetail = actionDetail;
        return this;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Boolean getStatus() {
        return status;
    }

    public PaymentLog setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    @Basic
    @Column(name = "log_time", nullable = true)
    public Timestamp getLogTime() {
        return logTime;
    }

    public PaymentLog setLogTime(Timestamp logTime) {
        this.logTime = logTime;
        return this;
    }

    @Basic
    @Column(name = "log_data", nullable = true)
    public String getLogData() {
        return logData;
    }

    public PaymentLog setLogData(String logData) {
        this.logData = logData;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentLog that = (PaymentLog) o;

        if (id != that.id) return false;
        if (requestId != null ? !requestId.equals(that.requestId) : that.requestId != null) return false;
        if (paymentId != null ? !paymentId.equals(that.paymentId) : that.paymentId != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (actionDetail != null ? !actionDetail.equals(that.actionDetail) : that.actionDetail != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (logTime != null ? !logTime.equals(that.logTime) : that.logTime != null) return false;
        if (logData != null ? !logData.equals(that.logData) : that.logData != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (requestId != null ? requestId.hashCode() : 0);
        result = 31 * result + (paymentId != null ? paymentId.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (actionDetail != null ? actionDetail.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (logTime != null ? logTime.hashCode() : 0);
        result = 31 * result + (logData != null ? logData.hashCode() : 0);
        return result;
    }
}

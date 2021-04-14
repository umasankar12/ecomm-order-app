package org.ecomm.foundation.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "OrderHistory", schema = "sales", catalog = "ecomm")
public class OrderHistory {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales.orderhistory_id_seq")
    @SequenceGenerator(name = "sales.orderhistory_id_seq",
      sequenceName = "sales.orderhistory_id_seq",
      initialValue = 1,
      allocationSize = 1
    )
    private int id;

    @Basic
    @Column(name = "user_type", nullable = true, length = 10)
    private String userType;

    @Basic
    @Column(name = "user_id", nullable = true)
    private Integer userId;

    @Basic
    @Column(name = "employee_id", nullable = true, length = 100)
    private String employeeId = "SYSTEM";

    @Basic
    @Column(name = "entry_time", nullable = true)
    private Timestamp entryTime;

    @Basic
    @Column(name = "action", nullable = true, length = 200)
    private String action;

    @Basic
    @Column(name = "action_detail", nullable = true, length = 1000)
    private String actionDetail;

    @Basic
    @Column(name = "status", nullable = true)
    private Boolean status;

    @Basic
    @Column(name = "notes", nullable = true, length = 2000)
    private String notes;

    @Basic
    @Column(name = "log_data", nullable = true)
    private String logData;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_item", referencedColumnName = "id")
    private OrderItem orderItem;

    public int getId() {
        return id;
    }

    public OrderHistory setId(int id) {
        this.id = id;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public OrderHistory setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public OrderHistory setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public OrderHistory setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public OrderHistory setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
        return this;
    }

    public String getAction() {
        return action;
    }

    public OrderHistory setAction(String action) {
        this.action = action;
        return this;
    }

    public String getActionDetail() {
        return actionDetail;
    }

    public OrderHistory setActionDetail(String actionDetail) {
        this.actionDetail = actionDetail;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public OrderHistory setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public OrderHistory setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getLogData() {
        return logData;
    }

    public OrderHistory setLogData(String logData) {
        this.logData = logData;
        return this;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public OrderHistory setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
        return this;
    }
}

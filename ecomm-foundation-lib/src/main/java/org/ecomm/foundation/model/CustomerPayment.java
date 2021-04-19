package org.ecomm.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(schema = "customer")
public class CustomerPayment {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Basic
    @Column(name = "end_date", nullable = true)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customerByCustomerId;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    @JsonIgnore
    private GuestCustomer customerByGuestId;


    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;


    public int getId() {
        return id;
    }

    public CustomerPayment setId(int id) {
        this.id = id;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public CustomerPayment setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public CustomerPayment setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public CustomerPayment setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
        return this;
    }

    public GuestCustomer getCustomerByGuestId() {
        return customerByGuestId;
    }

    public CustomerPayment setCustomerByGuestId(GuestCustomer customerByGuestId) {
        this.customerByGuestId = customerByGuestId;
        return this;
    }

    public Payment getPayment() {
        return payment;
    }

    public CustomerPayment setPayment(Payment payment) {
        this.payment = payment;
        return this;
    }
}

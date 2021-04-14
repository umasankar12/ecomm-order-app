package org.ecomm.foundation.model;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(schema = "customer")
public class CustomerPayment {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator="customer.customerpayment_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer.customerpayment_id_seq", sequenceName = "customer.customerpayment_id_seq", allocationSize = 1)
    private int id;

    @Basic
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Basic
    @Column(name = "end_date", nullable = true)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customerByCustomerId;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
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

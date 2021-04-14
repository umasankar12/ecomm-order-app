package org.ecomm.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(schema = "sales", name = "Order")
public class Order {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales.order_id_seq")
    @SequenceGenerator(name = "sales.order_id_seq", sequenceName = "sales.order_id_seq",
      initialValue = 5,
      allocationSize = 1)
    private int id;

    @Column(nullable = false)
    String source = "WEB";

    @Column(name = "source_id")
    String sourceId = UUID.randomUUID().toString();

    @Basic
    @Column(name = "total_amt", nullable = true, precision = 2)
    private Double totalAmt;

    @Basic
    @Column(name = "create_date", nullable = true)
    private Timestamp createDate;

    @Basic
    @Column(name = "cancel_date", nullable = true)
    private Timestamp cancelDate;

    @Basic
    @Column(name = "return_date", nullable = true)
    private Timestamp returnDate;

    @Basic
    @Column(name = "notes", nullable = true, length = 2000)
    private String notes;

    @Basic
    @Column(name = "status", nullable = true, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    private GuestCustomer guestCustomer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItem> items;



    public List<OrderItem> getItems() {
        return items;
    }

    public Order setItems(List<OrderItem> orderItems) {
        this.items = orderItems;
        return this;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public Order setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_address", referencedColumnName = "id")
    private Address shippingAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderPayment> payments;


    public int getId() {
        return id;
    }

    public Order setId(int id) {
        this.id = id;
        return this;
    }

    public Double getTotalAmt() {
        return totalAmt;
    }

    public Order setTotalAmt(Double totalAmt) {
        this.totalAmt = totalAmt;
        return this;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public Order setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
        return this;
    }

    public Timestamp getCancelDate() {
        return cancelDate;
    }

    public Order setCancelDate(Timestamp cancelDate) {
        this.cancelDate = cancelDate;
        return this;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public Order setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Order setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Order setStatus(String status) {
        this.status = status;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Order setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public GuestCustomer getGuestCustomer() {
        return guestCustomer;
    }

    public Order setGuestCustomer(GuestCustomer guestCustomer) {
        this.guestCustomer = guestCustomer;
        return this;
    }


    @Transient
    Customer inputCustomer;

    @Transient
    GuestCustomer inputGuest;

    public List<OrderPayment> getPayments() {
        return payments;
    }

    public Order setPayments(List<OrderPayment> payments) {
        this.payments = payments;
        return this;
    }

    public Customer getInputCustomer() {
        return inputCustomer;
    }

    public Order setInputCustomer(Customer inputCustomer) {
        this.inputCustomer = inputCustomer;
        return this;
    }

    public GuestCustomer getInputGuest() {
        return inputGuest;
    }

    public Order setInputGuest(GuestCustomer inputGuest) {
        this.inputGuest = inputGuest;
        return this;
    }
}

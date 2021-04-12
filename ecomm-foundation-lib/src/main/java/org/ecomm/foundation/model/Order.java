package org.ecomm.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(schema = "sales", name = "Order")
public class Order {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator="sales.order_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sales.order_id_seq", sequenceName = "sales.order_id_seq", allocationSize = 1)
    private int id;

    @Basic
    @Column(name = "total_amt", nullable = true, precision = 2)
    private BigInteger totalAmt;

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
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    @JsonIgnore
    private GuestCustomer guestCustomer;

}

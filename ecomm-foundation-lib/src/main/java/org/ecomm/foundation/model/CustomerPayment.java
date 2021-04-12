package org.ecomm.foundation.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
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
    private Customer customerByCustomerId;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private GuestCustomer customerByGuestId;


    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;


}

package org.ecomm.foundation.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name = "payment", schema = "customer")
public class Payment {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator="customer.payment_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer.payment_id_seq", sequenceName = "customer.payment_id_seq", allocationSize = 1)
    private int id;

    @Basic
    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Basic
    @Column(name = "cardholder", nullable = false, length = 100)
    private String cardholder;

    @Basic
    @Column(name = "card_num", nullable = false, length = 20)
    private String cardNum;

    @Basic
    @Column(name = "provider", nullable = false, length = 20)
    private String provider;

    @Basic
    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;

    @Basic
    @Column(name = "cvv", nullable = false)
    private int cvv;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billing_address", referencedColumnName = "id")
    private Address billingAddress;

}

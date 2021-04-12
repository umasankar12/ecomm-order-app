package org.ecomm.foundation.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "OrderPayment", schema = "sales", catalog = "ecomm")
public class OrderPayment {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "payment_id", nullable = true)
    private Integer paymentId;

    @Basic
    @Column(name = "payment_amt", nullable = true, precision = 2)
    private BigInteger paymentAmt;

    @Basic
    @Column(name = "confirm_code", nullable = true, length = 1000)
    private String confirmCode;

    @Basic
    @Column(name = "confirm_time", nullable = true)
    private Timestamp confirmTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order orderByOrderId;

}

package org.ecomm.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales.orderpayment_id_seq")
    @SequenceGenerator(name = "sales.orderpayment_id_seq", sequenceName = "sales.orderpayment_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @Column(name = "payment_type", nullable = false)
    String paymentType = "PAYMENT";

    @Basic
    @Column(name = "amount", nullable = true, precision = 2)
    private Double amount;

    @Column
    Boolean status;

    @Column(name = "payment_code", nullable = true, length = 1000)
    private String paymentCode;

    @Basic
    @Column(name = "response_code", nullable = true, length = 1000)
    private String responseCode;

    @Basic
    @Column(name = "reason_code", nullable = true, length = 1000)
    private String reasonCode;

    @Basic
    @Column(name = "request_time", nullable = true)
    private Timestamp requestTime;

    @Basic
    @Column(name = "response_time", nullable = true)
    private Timestamp responseTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnore
    private Order order;

}

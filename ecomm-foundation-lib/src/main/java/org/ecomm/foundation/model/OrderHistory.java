package org.ecomm.foundation.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "OrderHistory", schema = "sales", catalog = "ecomm")
public class OrderHistory {
    @Id
    @Column(name = "id", nullable = false)
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

    @ManyToOne
    @JoinColumn(name = "order_item", referencedColumnName = "id")
    private OrderItem orderItem;

}

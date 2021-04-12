package org.ecomm.foundation.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(schema = "inventory")
public class Item {

    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "code", nullable = false, length = 20)
    private String code;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = 1000)
    private String description;
    @Column(name = "unit")
    private String unit;
    @Column(name = "unit_price", precision = 2)
    private BigInteger unitPrice;
    @Column(name = "avail_qty")
    private BigInteger availQty;
    @Column(name = "tags")
    private String tags;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

}

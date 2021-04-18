package org.ecomm.foundation.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(schema = "inventory")
public class Item {

    public static enum PROP {NAME, DESCRIPTION, QUANTITY, UNI_PRICE, TAGS, START_DATE, END_DATE}

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory.item_id_seq")
    @SequenceGenerator(name = "inventory.item_id_seq", sequenceName = "inventory.item_id_seq",
      initialValue = 100,
      allocationSize = 1)
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

    public int getId() {
        return id;
    }

    public Item setId(int id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Item setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public Item setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public BigInteger getUnitPrice() {
        return unitPrice;
    }

    public Item setUnitPrice(BigInteger unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public BigInteger getAvailQty() {
        return availQty;
    }

    public Item setAvailQty(BigInteger availQty) {
        this.availQty = availQty;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public Item setTags(String tags) {
        this.tags = tags;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Item setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Item setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

}

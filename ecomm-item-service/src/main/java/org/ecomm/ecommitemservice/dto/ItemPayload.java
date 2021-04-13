package org.ecomm.ecommitemservice.dto;

import java.math.BigInteger;

public class ItemPayload {

    private int id;
    private String code;
    private String name;
    private String description;
    private String unit;
    private BigInteger unitPrice;
    private BigInteger availQty;
    private String tags;

    public int getId() {
        return id;
    }

    public ItemPayload setId(int id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ItemPayload setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemPayload setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemPayload setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public ItemPayload setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public BigInteger getUnitPrice() {
        return unitPrice;
    }

    public ItemPayload setUnitPrice(BigInteger unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public BigInteger getAvailQty() {
        return availQty;
    }

    public ItemPayload setAvailQty(BigInteger availQty) {
        this.availQty = availQty;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public ItemPayload setTags(String tags) {
        this.tags = tags;
        return this;
    }
}

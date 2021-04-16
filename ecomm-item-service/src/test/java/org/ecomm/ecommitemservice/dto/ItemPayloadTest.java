package org.ecomm.ecommitemservice.dto;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
class ItemPayloadTest {

    private int id;
    private String code;
    private String name;
    private String description;
    private String unit;
    private BigInteger unitPrice;
    private BigInteger availQty;
    private String tags;

    final ItemPayload item = new ItemPayload()
            .setId(1)
            .setCode("1212")
            .setName("Sugar")
            .setDescription("1212")
            .setTags("1234564")
            .setUnit("14")
            .setAvailQty(BigInteger.TEN)
            .setUnitPrice(BigInteger.TEN);


    @Test
    void getId() {
        assertEquals(item.getId(), 1);
    }

    @Test
    void getCode() {
        assertEquals(item.getCode(),"1212");
    }

    @Test
    void getName() {
        assertEquals(item.getName(),"Sugar");
    }


    @Test
    void getDescription() {
        assertEquals(item.getDescription(),"1212");
    }

    @Test
    void getUnit() {
        assertEquals(item.getUnit(),"14");
    }

    @Test
    void getUnitPrice() {
        assertEquals(item.getUnitPrice(),BigInteger.TEN);
    }

    @Test
    void getAvailQty() {
        assertEquals(item.getAvailQty(),BigInteger.TEN);
    }

    @Test
    void getTags() {
        assertEquals(item.getTags(),"1234564");
    }

}
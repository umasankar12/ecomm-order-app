package org.ecomm.foundation.model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
public class AddressTest {
    final String line1 = "l1";
    final String line2 = "l2";
    final String city = "manhattan";
    final String state = "NJ";
    final String country = "USA";
    final String zip = "10101";
    final String nickname = "TESTADDRESS";
    final Address address = new Address()
      .setId(1)
      .setLine1(line1)
      .setLine2(line2)
      .setCity(city)
      .setState(state)
      .setCountry(country)
      .setZipcode(zip)
      .setNickname(nickname);

    @Test
    public void getId() {
        assertEquals(address.getId(), 1);
    }

    @Test
    public void getLine1() {
        assertEquals(address.getLine1(), line1);
    }

    @Test
    public void getLine2() {
        assertEquals(address.getLine2(), line2);
    }

    @Test
    public void getCity() {
        assertEquals(address.getCity(), city);
    }

    @Test
    public void getState() {
        assertEquals(address.getState(), state);
    }

    @Test
    public void getCountry() {
        assertEquals(address.getCountry(), country);
    }

    @Test
    public void getZipcode() {
        assertEquals(address.getZipcode(), zip);
    }

    @Test
    public void getType() {
        assertNull(address.getType()); //not used
    }

    @Test
    public void getNickname() {
        assertEquals(address.getNickname(), nickname);
    }

    @Test
    public void getCustomers() {
        assertNotNull(address.getCustomers());
        assertEquals(address.getCustomers().size(), 0);
    }

    @Test
    public void testToString() {
        assertNotNull(address.toString());
    }
}
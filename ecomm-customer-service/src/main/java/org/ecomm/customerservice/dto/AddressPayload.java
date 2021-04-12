package org.ecomm.customerservice.dto;

import org.ecomm.foundation.model.Customer;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;

public class AddressPayload {

    private String line1;

    private String line2;

    private String city;

    private String state;

    private String country;

    private String zipcode;

    private String type;

    private String nickname;

    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public AddressPayload setCustomerId(int customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getLine1() {
        return line1;
    }

    public AddressPayload setLine1(String line1) {
        this.line1 = line1;
        return this;
    }

    public String getLine2() {
        return line2;
    }

    public AddressPayload setLine2(String line2) {
        this.line2 = line2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressPayload setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public AddressPayload setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddressPayload setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getZipcode() {
        return zipcode;
    }

    public AddressPayload setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public String getType() {
        return type;
    }

    public AddressPayload setType(String type) {
        this.type = type;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public AddressPayload setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }


}

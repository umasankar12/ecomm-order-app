package org.ecomm.customerservice.dto;

public class GuestCustomerPayload {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNum;
    private String email;

    public int getId() {
        return id;
    }

    public GuestCustomerPayload setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public GuestCustomerPayload setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public GuestCustomerPayload setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public GuestCustomerPayload setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public GuestCustomerPayload setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public GuestCustomerPayload setEmail(String email) {
        this.email = email;
        return this;
    }
}

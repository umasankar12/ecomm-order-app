package org.ecomm.customerservice.dto;


import java.sql.Date;

public class CustomerPayload {
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private String email;
    private Date dob;
    private int paymentId;

    public int getPaymentId() {
        return paymentId;
    }

    public CustomerPayload setPaymentId(int paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CustomerPayload setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerPayload setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public CustomerPayload setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerPayload setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerPayload setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getDob() {
        return dob;
    }

    public CustomerPayload setDob(Date dob) {
        this.dob = dob;
        return this;
    }
}

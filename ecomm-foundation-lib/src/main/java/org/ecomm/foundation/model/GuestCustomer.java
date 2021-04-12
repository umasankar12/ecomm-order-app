package org.ecomm.foundation.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
//@Getter
//@Setter
@EqualsAndHashCode
@Entity
@Table(schema = "customer", name = "GuestCustomer")
public class GuestCustomer {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Basic
    @Column(name = "gender", nullable = true, length = 1)
    private String gender;

    @Basic
    @Column(name = "phone_num", nullable = true, length = 20)
    private String phoneNum;

    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guestCustomer")
    private List<Order> orders = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CustomerAddress", schema = "customer",
      joinColumns = @JoinColumn(name = "customer_id"),
      inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses = new ArrayList<>();

    public int getId() {
        return id;
    }

    public GuestCustomer setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public GuestCustomer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public GuestCustomer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public GuestCustomer setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public GuestCustomer setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public GuestCustomer setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public GuestCustomer setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public GuestCustomer setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }
}

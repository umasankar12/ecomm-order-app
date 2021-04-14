package org.ecomm.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(schema = "customer")
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator="customer.customer_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer.customer_id_seq", sequenceName = "customer.customer_id_seq", allocationSize = 1)
    private int id;

    @Basic
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Basic
    @Column(name = "gender", nullable = true, length = 1)
    private String gender;

    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;

    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;

    @Basic
    @Column(name = "dob", nullable = true)
    private Date dob;

    @Basic
    @Column(name = "primary_address", nullable = true)
    //TODO: Delete primaryAddress column
    private Integer primaryAddress = 1;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @JsonIgnore
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

    public Customer setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Customer setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getDob() {
        return dob;
    }

    public Customer setDob(Date dob) {
        this.dob = dob;
        return this;
    }

    public Integer getPrimaryAddress() {
        return primaryAddress;
    }

    public Customer setPrimaryAddress(Integer primaryAddress) {
        this.primaryAddress = primaryAddress;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Customer setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public Customer setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }
}

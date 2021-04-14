package org.ecomm.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(schema = "customer")
public class Address {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator="customer.address_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer.address_id_seq", sequenceName = "customer.address_id_seq", allocationSize = 1)
    private int id;

    @Basic
    @Column(name = "line1", nullable = false, length = 200)
    private String line1;

    @Basic
    @Column(name = "line2", nullable = true, length = 200)
    private String line2;

    @Basic
    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Basic
    @Column(name = "state", nullable = false, length = 100)
    private String state;

    @Basic
    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @Basic
    @Column(name = "zipcode", nullable = false, length = 10)
    private String zipcode;

    @Basic
    @Column(name = "type", nullable = true, length = 20)
    private String type;

    @Basic
    @Column(name = "nickname", nullable = true, length = 200)
    private String nickname;

    @ManyToMany(mappedBy = "addresses", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Customer> customers = new HashSet<>();

    public int getId() {
        return id;
    }

    public Address setId(int id) {
        this.id = id;
        return this;
    }

    public String getLine1() {
        return line1;
    }

    public Address setLine1(String line1) {
        this.line1 = line1;
        return this;
    }

    public String getLine2() {
        return line2;
    }

    public Address setLine2(String line2) {
        this.line2 = line2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Address setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Address setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public String getType() {
        return type;
    }

    public Address setType(String type) {
        this.type = type;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public Address setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public Address setCustomers(Set<Customer> customers) {
        this.customers = customers;
        return this;
    }
    
}

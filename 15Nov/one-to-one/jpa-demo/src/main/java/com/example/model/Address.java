package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String zipcode;

    @OneToOne(mappedBy = "address")
    private User user;

    // constructors, getters, setters
    public Address() {}
    public Address(String street, String city, String zipcode) {
        this.street = street; this.city = city; this.zipcode = zipcode;
    }
    public Long getId() { return id; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}

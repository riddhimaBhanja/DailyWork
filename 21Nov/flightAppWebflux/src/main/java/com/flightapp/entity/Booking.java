package com.flightapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String pnr;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_id")
    private FlightInventory flight;

    @Column(nullable = false, length = 100)
    private String userName;

    @Column(nullable = false, length = 100)
    private String userEmail;

    @Column(nullable = false)
    private LocalDate journeyDate;

    @Column(nullable = false)
    private Integer noOfSeats;

    @Column(length = 10)
    private String mealType;

    @Column(nullable = false, length = 15)
    private String status;   // CONFIRMED / CANCELLED

    @Column(name = "booking_date_time", nullable = false)
    private LocalDateTime bookingDateTime;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Passenger> passengers;
}
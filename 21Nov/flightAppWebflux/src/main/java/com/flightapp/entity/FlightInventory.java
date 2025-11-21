package com.flightapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "flight_inventory")
public class FlightInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @Column(nullable = false, length = 20)
    private String flightNumber;

    @Column(nullable = false, length = 50)
    private String fromPlace;

    @Column(nullable = false, length = 50)
    private String toPlace;

    @Column(nullable = false)
    private LocalDateTime departureDateTime;

    @Column(nullable = false)
    private LocalDateTime arrivalDateTime;

    @Column(nullable = false)
    private Integer totalSeats;

    @Column(nullable = false)
    private Integer availableSeats;

    @Column(nullable = false)
    private Double oneWayPrice;

    private Double roundTripPrice;

    private Boolean mealAvailable;
}
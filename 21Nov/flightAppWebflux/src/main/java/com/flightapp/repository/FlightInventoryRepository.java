package com.flightapp.repository;

import com.flightapp.entity.FlightInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightInventoryRepository extends JpaRepository<FlightInventory, Long> {

    List<FlightInventory> findByFromPlaceAndToPlaceAndDepartureDateTimeBetween(
            String fromPlace,
            String toPlace,
            LocalDateTime start,
            LocalDateTime end);
}
package com.flightapp.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class BookRequest {
    private String passengerName;
    private String userEmail;
    private LocalDate journeyDate;
    private Integer noOfSeats;
    private String mealType;
    private List<PassengerRequest> passengers;
}

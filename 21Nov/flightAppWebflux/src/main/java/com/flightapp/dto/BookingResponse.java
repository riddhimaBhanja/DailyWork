package com.flightapp.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingResponse {
    private String pnr;
    private String userName;
    private String userEmail;
    private LocalDate journeyDate;
    private Integer noOfSeats;
    private String mealType;
    private String status;
    private LocalDateTime bookingDateTime;
    private List<PassengerResponse> passengers;
}

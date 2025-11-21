package com.flightapp.controller;

import com.flightapp.dto.*;
import com.flightapp.entity.FlightInventory;
import com.flightapp.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1.0/flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/airline/inventory/add")
    public <FlightInventory> Mono<FlightInventory> addInventory(@RequestBody InventoryRequest request) {
        return (Mono<FlightInventory>) flightService.addInventory(request);
    }


	
	  // 2) Search flights
	  
	  @PostMapping("/search") public Flux<FlightInventory>
	  searchFlights(@Valid @RequestBody FlightSearchRequest request) { return
	  flightService.searchFlights(request); }
	 
	
	 // 3) Book ticket
	 
	 @PostMapping("/booking/{flightId}") public Mono<BookingResponse>
	 bookTicket(@PathVariable Long flightId,
	 
	 @Valid @RequestBody BookRequest request) { return
	 flightService.bookTicket(flightId, request); }
	 
	 // 4) Get ticket by PNR
	 
	 @GetMapping("/ticket/{pnr}") public Mono<BookingResponse>
	 getTicket(@PathVariable String pnr) { return
	 flightService.getTicketByPnr(pnr); }
	 
	 // 5) Booking history
	 
	 @GetMapping("/booking/history/{emailId}")
	 public Flux<BookingResponse> history(@PathVariable String emailId) {
	     return flightService.getBookingHistory(emailId);
	 }

	 // 6) Cancel ticket
	  
	 @DeleteMapping("/cancel/{pnr}")
	 public Mono<BookingResponse> cancelTicket(@PathVariable String pnr) {
	     return flightService.cancelTicket(pnr);
	 }


}

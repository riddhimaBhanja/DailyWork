package com.flightapp.service.impl;

import reactor.core.publisher.Flux;

import com.flightapp.dto.*;

import com.flightapp.entity.Booking;
import com.flightapp.entity.FlightInventory;
import com.flightapp.entity.Passenger;
import com.flightapp.exception.BusinessException;
import com.flightapp.exception.ResourceNotFoundException;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.FlightInventoryRepository;
import com.flightapp.repository.PassengerRepository;
import com.flightapp.service.FlightService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
	private static final String STATUS_CANCELLED = "CANCELLED";


	private final AirlineRepository airlineRepository;
	private final FlightInventoryRepository flightInventoryRepository;
	
	private final BookingRepository bookingRepository;
	private final PassengerRepository passengerRepository;

	@Override
	public Mono<FlightInventory> addInventory(InventoryRequest request) {
		return Mono.fromCallable(() -> airlineRepository.findByCode(request.getAirlineCode())
				.orElseThrow(() -> new RuntimeException("Airline not found"))).map(airline -> {
					FlightInventory inventory = new FlightInventory();
					inventory.setAirline(airline);
					inventory.setFlightNumber(request.getFlightNumber());
					inventory.setFromPlace(request.getFromPlace());
					inventory.setToPlace(request.getToPlace());
					inventory.setDepartureDateTime(request.getDepartureDateTime());
					inventory.setArrivalDateTime(request.getArrivalDateTime());
					inventory.setTotalSeats(request.getTotalSeats());
					inventory.setAvailableSeats(request.getTotalSeats());
					inventory.setOneWayPrice(request.getOneWayPrice());
					inventory.setRoundTripPrice(request.getRoundTripPrice());
					inventory.setMealAvailable(request.getMealAvailable());
					return inventory;
				}).flatMap(inv -> Mono.fromCallable(() -> flightInventoryRepository.save(inv)));
	}

	@Override
	public Flux<FlightInventory> searchFlights(FlightSearchRequest request) {
		LocalDate journeyDate = request.getJourneyDate();

		return Mono.fromCallable(() -> flightInventoryRepository.findByFromPlaceAndToPlaceAndDepartureDateTimeBetween(
				request.getFromPlace(), request.getToPlace(), journeyDate.atStartOfDay(),
				journeyDate.plusDays(1).atStartOfDay())).flatMapMany(Flux::fromIterable);
	}
	private String generatePnr() {
	    return "PNR" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
	}


	

	@Override
	public Mono<BookingResponse> bookTicket(Long flightId, BookRequest request) {

	    return Mono.fromCallable(() ->
	            flightInventoryRepository.findById(flightId)
	                    .orElseThrow(() -> new ResourceNotFoundException("Flight not found"))
	    )
	    .flatMap(flight -> {

	        if (flight.getAvailableSeats() < request.getNoOfSeats()) {
	            return Mono.error(new BusinessException("Not enough seats"));
	        }

	        Booking booking = new Booking();
	        booking.setPnr(generatePnr());
	        booking.setFlight(flight);
	        booking.setUserName(request.getPassengerName());
	        booking.setUserEmail(request.getUserEmail());
	        booking.setJourneyDate(request.getJourneyDate());
	        booking.setNoOfSeats(request.getNoOfSeats());
	        booking.setMealType(request.getMealType());
	        booking.setStatus("CONFIRMED");
	        booking.setBookingDateTime(LocalDateTime.now());

	        return Mono.fromCallable(() -> bookingRepository.save(booking))
	                .flatMap(savedBooking -> {

	                    List<Passenger> passengers = request.getPassengers().stream()
	                            .map(p -> {
	                                Passenger ps = new Passenger();
	                                ps.setBooking(savedBooking);
	                                ps.setName(p.getName());
	                                ps.setGender(p.getGender());
	                                ps.setAge(p.getAge());
	                                ps.setSeatNumber(p.getSeatNumber());
	                                return ps;
	                            }).toList();

	                    return Mono.fromCallable(() -> passengerRepository.saveAll(passengers))
	                            .then(Mono.fromCallable(() -> {
	                                savedBooking.setPassengers(passengers);
	                                flight.setAvailableSeats(flight.getAvailableSeats() - request.getNoOfSeats());
	                                flightInventoryRepository.save(flight);
	                                return mapToResponse(savedBooking);
	                            }));
	                });
	    });
	}
	private BookingResponse mapToResponse(Booking booking) {
	    BookingResponse response = new BookingResponse();
	    response.setPnr(booking.getPnr());
	    response.setUserName(booking.getUserName());
	    response.setUserEmail(booking.getUserEmail());
	    response.setJourneyDate(booking.getJourneyDate());
	    response.setNoOfSeats(booking.getNoOfSeats());
	    response.setMealType(booking.getMealType());
	    response.setStatus(booking.getStatus());
	    response.setBookingDateTime(booking.getBookingDateTime());

	    response.setPassengers(
	        booking.getPassengers().stream().map(p -> {
	            PassengerResponse pr = new PassengerResponse();
	            pr.setName(p.getName());
	            pr.setGender(p.getGender());
	            pr.setAge(p.getAge());
	            pr.setSeatNumber(p.getSeatNumber());
	            return pr;
	        }).toList()
	    );

	    return response;
	}

	    

	  
	@Override
	@Transactional
	public Mono<BookingResponse> getTicketByPnr(String pnr) {
	    return Mono.fromCallable(() ->
	        bookingRepository.findByPnr(pnr)
	                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with PNR: " + pnr))
	    ).map(this::mapToResponse);
	}

	 
	public Flux<BookingResponse> getBookingHistory(String email) {
	    return Mono.fromCallable(() ->
	        bookingRepository.findByUserEmailOrderByBookingDateTimeDesc(email)
	    ).flatMapMany(list -> Flux.fromIterable(
	            list.stream().map(this::mapToResponse).toList()
	    ));
	}
	@Override
    @Transactional
    public Mono<BookingResponse> cancelTicket(String pnr) {
        return Mono.fromCallable(() ->
                bookingRepository.findByPnr(pnr)
                        .orElseThrow(() -> new ResourceNotFoundException("Booking not found"))
        ).flatMap(booking -> {

            if (booking.getStatus().equalsIgnoreCase(STATUS_CANCELLED)) {
                return Mono.error(new BusinessException("Already cancelled"));
            }
            if ("CANCELLED".equals(booking.getStatus())) {
            	return Mono.just(mapToResponse(booking));
            }


            booking.setStatus(STATUS_CANCELLED);

            FlightInventory flight = booking.getFlight();
            flight.setAvailableSeats(flight.getAvailableSeats() + booking.getNoOfSeats());
            flightInventoryRepository.save(flight);
            bookingRepository.save(booking);

            return Mono.just(mapToResponse(booking));
            
        });
    }
}
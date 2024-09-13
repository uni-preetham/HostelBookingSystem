package com.crimsonlogic.hostelmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.hostelmanagementsystem.entity.Booking;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/createbooking")
	public Booking createBooking(@RequestBody Booking booking) {
		return bookingService.createBooking(booking);
	}
	
	@GetMapping("/listallbookings")
	public List<Booking> listAllBookings(){
		return bookingService.listAllBooking();
	}
	
	@GetMapping("/showbookingbyid/{bookingId}")
	public Booking showBookingById(@PathVariable("bookingId") String bookingId) {
		return bookingService.showBookingById(bookingId);
	}
	
	@PutMapping("/updatebooking/{bookingId}")
	public void updateBooking(@PathVariable("bookingId") String bookingId, @RequestBody Booking booking) throws ResourceNotFoundException{
		
	}
}

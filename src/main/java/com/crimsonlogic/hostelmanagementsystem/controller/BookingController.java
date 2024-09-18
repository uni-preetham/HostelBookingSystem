package com.crimsonlogic.hostelmanagementsystem.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crimsonlogic.hostelmanagementsystem.entity.Booking;
import com.crimsonlogic.hostelmanagementsystem.entity.Room;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.service.BookingService;
import com.crimsonlogic.hostelmanagementsystem.service.RoomService;


/**
 * Represents a booking in the hostel management system.
 * Author: Preetham A A
 */

@Controller
@RequestMapping("/booking")
public class BookingController {

	private static final Logger LOG = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
    private BookingService bookingService;
	
	@Autowired
    private RoomService roomService;

    
	/*
	 * Displays the hostel booking form.
	 * 
	 */
    @GetMapping("/showbookingform/{roomId}")
    public String showBookingForm(@PathVariable("roomId") String roomId, Model model) {

        Room room = roomService.showRoomById(roomId);
        if (room == null) {
            model.addAttribute("error", "Room not found");
            return "errorPage";
        }
        model.addAttribute("room", room);
        return "roombooking";
    }
    
    /*
	 * Booking the room.
	 * 
	 */
    @PostMapping("/confirm")
    public String confirmBooking(
            @RequestParam("roomId") String roomId,
            @RequestParam("tenantId") String tenantId,
            @RequestParam("checkInDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkInDate,
            @RequestParam("checkOutDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOutDate,
            Model model) throws ResourceNotFoundException {
    	LOG.debug("inside confirmBooking handler method");
        Booking booking = bookingService.confirmBooking(roomId, tenantId, checkInDate, checkOutDate);
        
        
        model.addAttribute("booking", booking);
        return "redirect:/payment/showpaymentform?bookingId=" + booking.getBookingId();
    }
    
    /*
	 * Displays the bookings by user.
	 * 
	 */
    @GetMapping("/viewbooking/{tenantId}")
    public String showBookingsByTenantId(@PathVariable("tenantId") String tenantId, Model model) {
        List<Booking> bookings = bookingService.getBookingsByTenant(tenantId);
        if (bookings.isEmpty()) {
            model.addAttribute("message", "No bookings found for this tenant.");
        } else {
            model.addAttribute("bookings", bookings);
        }
        return "tenantbooking";  // This will be the name of your JSP page
    }

	
}

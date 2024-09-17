package com.crimsonlogic.hostelmanagementsystem.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crimsonlogic.hostelmanagementsystem.entity.Booking;
import com.crimsonlogic.hostelmanagementsystem.entity.Payment;
import com.crimsonlogic.hostelmanagementsystem.entity.Room;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.service.BookingService;
import com.crimsonlogic.hostelmanagementsystem.service.PaymentService;
import com.crimsonlogic.hostelmanagementsystem.service.RoomService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private BookingService bookingService;
    
	@Autowired
    private RoomService roomService;
    
    @GetMapping("/showpaymentform")
    public String showPaymentForm(@RequestParam("bookingId") String bookingId, Model model) {
		Booking booking = bookingService.showBookingById(bookingId); // Fetch booking details
        model.addAttribute("booking", booking);
        return "paymentpage"; // JSP page to display payment form
    }
    
    
    @PostMapping("/confirmpayment")
    public String confirmPayment(
            @RequestParam("bookingId") String bookingId,
            @RequestParam("paymentAmount") Double paymentAmount,
            @RequestParam("paymentMethod") String paymentMethod,
            Model model) throws ResourceNotFoundException {

        // Retrieve the booking
        Booking booking = bookingService.showBookingById(bookingId);

        if (booking != null && paymentAmount != null && paymentAmount.equals(booking.getTotalPrice())) {
            // Update booking status to 'confirmed'
            booking.setStatus("confirmed");
            bookingService.updateBooking(bookingId, booking);

            // Create and save payment record
            Payment payment = new Payment(); // Implement this method to generate unique payment IDs
            payment.setAmount(paymentAmount);
            payment.setPaymentDate(new Date());
            payment.setPaymentMethod(paymentMethod);
            payment.setStatus("paid"); // Set status to 'paid'

            // Associate payment with booking
            payment.setBooking(booking);
            paymentService.processPayment(payment); // Save payment record

            // Retrieve room associated with the booking
            Room room = booking.getRoom();  // Assuming you have a relationship between Booking and Room
            if (room != null) {
                room.setAvailability(false);  // Set room availability to false
                roomService.updateRoom(room.getRoomId(), room);  // Update the room record in the database
            } else {
                model.addAttribute("error", "Room not found for booking.");
                return "errorPage";
            }

            // Redirect to a confirmation page or dashboard
            return "paymentcompleted";
        } else {
            // Handle payment failure
            model.addAttribute("error", "Payment amount does not match the total price.");
            return "paymentform"; // Return to payment form with an error
        }
    }

   

    // Create a new payment
    @PostMapping("/createpayment")
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }

    // Get a list of all payments
    @GetMapping("/listpayments")
    public List<Payment> listAllPayments() {
        return paymentService.listAllPayments();
    }

    // Get a specific payment by ID
    @GetMapping("/showpaymentbyid/{paymentId}")
    public Payment showPaymentById(@PathVariable("paymentId") String paymentId) throws ResourceNotFoundException {
        return paymentService.showPaymentById(paymentId);
    }

    // Update an existing payment by ID
    @PutMapping("/updatepayment/{paymentId}")
    public void updatePayment(@PathVariable("paymentId") String paymentId, @RequestBody Payment paymentDetails) throws ResourceNotFoundException {
        paymentService.updatePayment(paymentId, paymentDetails);
    }

    // Delete a payment by ID
    @DeleteMapping("/deletepayment/{paymentId}")
    public void deletePayment(@PathVariable("paymentId") String paymentId) throws ResourceNotFoundException {
        paymentService.deletePayment(paymentId);
    }
}

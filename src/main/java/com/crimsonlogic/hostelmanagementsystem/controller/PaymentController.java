package com.crimsonlogic.hostelmanagementsystem.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crimsonlogic.hostelmanagementsystem.entity.Booking;
import com.crimsonlogic.hostelmanagementsystem.entity.Payment;
import com.crimsonlogic.hostelmanagementsystem.entity.Room;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.service.BookingService;
import com.crimsonlogic.hostelmanagementsystem.service.InvoiceService;
import com.crimsonlogic.hostelmanagementsystem.service.PaymentService;
import com.crimsonlogic.hostelmanagementsystem.service.RoomService;
import com.itextpdf.text.DocumentException;


/**
 * Represents a payment in the hostel management system.
 * Author: Preetham A A
 */

@Controller
@RequestMapping("/payment")
public class PaymentController {

	private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);
	
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private BookingService bookingService;
    
	@Autowired
    private RoomService roomService;
	
	@Autowired
	private InvoiceService invoiceService;

	/*
	 * Displays the payment form for a specific booking identified by bookingId.
	 * 
	 */
    @GetMapping("/showpaymentform")
    public String showPaymentForm(@RequestParam("bookingId") String bookingId, Model model) {
    	LOG.debug("inside confirmBooking handler method");
		Booking booking = bookingService.showBookingById(bookingId); // Fetch booking details
        model.addAttribute("booking", booking);
        return "paymentpage"; // JSP page to display payment form
    }
    
	/*
	 * Confirms the payment for a booking based on the provided booking ID, amount,
	 * and payment method.
	 */ 
    @PostMapping("/confirmpayment")
    public String confirmPayment(
            @RequestParam("bookingId") String bookingId,
            @RequestParam("paymentAmount") Double paymentAmount,
            @RequestParam("paymentMethod") String paymentMethod,
            Model model) throws ResourceNotFoundException {

        Booking booking = bookingService.showBookingById(bookingId);

        if (booking != null && paymentAmount != null && paymentAmount.equals(booking.getTotalPrice())) {
            booking.setStatus("confirmed");
            bookingService.updateBooking(bookingId, booking);
            Payment payment = new Payment();
            payment.setAmount(paymentAmount);
            payment.setPaymentDate(new Date());
            payment.setPaymentMethod(paymentMethod);
            payment.setStatus("paid"); 
            payment.setBooking(booking);
            paymentService.processPayment(payment); 
            Room room = booking.getRoom();
            if (room != null) {
                room.setAvailability(false);
                roomService.updateRoom(room.getRoomId(), room);
            } else {
                model.addAttribute("error", "Room not found for booking.");
                return "errorPage";
            }
            model.addAttribute("bookingId", bookingId);
            return "paymentcompleted";
        } else {
            model.addAttribute("error", "Payment amount does not match the total price.");
            return "paymentform";
        }
    }
    
    
	/*
	 * 
	 * Downloads the invoice for a booking identified by bookingId.
	 */
    @GetMapping("/downloadInvoice")
    public void downloadInvoice(
            @RequestParam("bookingId") String bookingId,
            HttpServletResponse response) throws ResourceNotFoundException, DocumentException, IOException {
        Booking booking = bookingService.showBookingById(bookingId);
        if (booking != null) {
            Payment payment = paymentService.findPaymentByBookingId(bookingId);
            if (payment != null) {
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=invoice_" + bookingId + ".pdf");
                invoiceService.generateInvoice(response, booking, payment);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Payment not found for the booking.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Booking not found.");
        }
    }
}

package com.crimsonlogic.hostelmanagementsystem.service;

import java.util.List;

import com.crimsonlogic.hostelmanagementsystem.entity.Payment;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;

public interface PaymentService {
    Payment processPayment(Payment payment);
    List<Payment> listAllPayments();
    Payment showPaymentById(String paymentId) throws ResourceNotFoundException;
    void updatePayment(String paymentId, Payment payment) throws ResourceNotFoundException;
    void deletePayment(String paymentId) throws ResourceNotFoundException;
	Payment findPaymentByBookingId(String bookingId) throws ResourceNotFoundException;
}

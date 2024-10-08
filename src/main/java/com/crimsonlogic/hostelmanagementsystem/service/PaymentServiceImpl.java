package com.crimsonlogic.hostelmanagementsystem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.hostelmanagementsystem.entity.Payment;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.repository.PaymentRepository;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    
    //Add payment details
    @Override
    public Payment processPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    
    //List all payments made
    @Override
    public List<Payment> listAllPayments() {
        return paymentRepository.findAll();
    }

    
    //Show payment by payment id
    @Override
    public Payment showPaymentById(String paymentId) throws ResourceNotFoundException {
        return paymentRepository.findById(paymentId).get();
    }

    
    //Update payment by payment id
    @Override
    public void updatePayment(String paymentId, Payment payment) throws ResourceNotFoundException {
        Payment existingPayment = showPaymentById(paymentId);
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setPaymentDate(payment.getPaymentDate());
        existingPayment.setPaymentMethod(payment.getPaymentMethod());
        existingPayment.setStatus(payment.getStatus());
        existingPayment.setBooking(payment.getBooking());
        paymentRepository.save(existingPayment);
    }

    
    //Delete payment by payment id
    @Override
    public void deletePayment(String paymentId) throws ResourceNotFoundException {
        Payment existingPayment = showPaymentById(paymentId);
        if (existingPayment != null) {
            paymentRepository.delete(existingPayment);
        } else {
            throw new ResourceNotFoundException("Payment not found with ID: " + paymentId);
        }
    }
    
    
    //Find payment by booking id
    @Override
    public Payment findPaymentByBookingId(String bookingId) throws ResourceNotFoundException {
        Payment payment = paymentRepository.findByBookingBookingId(bookingId);
        
        if (payment == null) {
            throw new ResourceNotFoundException("Payment not found for booking ID: " + bookingId);
        }
        
        return payment;
    }
}

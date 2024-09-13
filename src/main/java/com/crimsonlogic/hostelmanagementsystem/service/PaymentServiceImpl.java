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

    @Override
    public Payment processPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> listAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment showPaymentById(String paymentId) throws ResourceNotFoundException {
        return paymentRepository.findById(paymentId).get();
    }

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

    @Override
    public void deletePayment(String paymentId) throws ResourceNotFoundException {
        Payment existingPayment = showPaymentById(paymentId);
        if (existingPayment != null) {
            paymentRepository.delete(existingPayment);
        } else {
            throw new ResourceNotFoundException("Payment not found with ID: " + paymentId);
        }
    }
}

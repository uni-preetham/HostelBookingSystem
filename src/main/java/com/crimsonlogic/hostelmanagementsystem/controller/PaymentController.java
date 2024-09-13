package com.crimsonlogic.hostelmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.hostelmanagementsystem.entity.Payment;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.service.PaymentService;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

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

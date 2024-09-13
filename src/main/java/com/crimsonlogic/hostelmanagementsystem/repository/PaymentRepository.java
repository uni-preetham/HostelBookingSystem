package com.crimsonlogic.hostelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.hostelmanagementsystem.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String>{

}

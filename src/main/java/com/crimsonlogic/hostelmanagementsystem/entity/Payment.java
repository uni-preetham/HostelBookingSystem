package com.crimsonlogic.hostelmanagementsystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_date")
    private Timestamp paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;  // Should be 'credit_card', 'debit_card', 'net_banking', 'UPI'

    @Column(name = "status")
    private String status = "pending";  // Should be 'pending', 'paid', or 'failed'
    
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
}

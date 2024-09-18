package com.crimsonlogic.hostelmanagementsystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

/**
 * Represents a booking in the hostel management system.
 * Author: Preetham A A
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
	@Id
	@Column(name = "booking_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id-generator")
	@GenericGenerator(name = "custom-id-generator", strategy = "com.crimsonlogic.hostelmanagementsystem.util.BookingIdGenerator")
	private String bookingId;

	@Column(name = "check_in_date")
	private Date checkInDate;

	@Column(name = "check_out_date")
	private Date checkOutDate;

	@Column(name = "total_price")
	private Double totalPrice;

	@Column(name = "status")
	private String status = "pending"; // Should be 'pending', 'confirmed', or 'cancelled'

	@Column(name = "booking_date", updatable = false)
	private Date bookingDate;

	@ManyToOne
	@JoinColumn(name = "tenant_id", nullable = false)
	private Tenant tenant;

	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;
}

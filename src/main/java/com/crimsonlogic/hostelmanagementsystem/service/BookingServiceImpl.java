
package com.crimsonlogic.hostelmanagementsystem.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.hostelmanagementsystem.repository.BookingRepository;
import com.crimsonlogic.hostelmanagementsystem.entity.Booking;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;

@Service

@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	

	@Override
	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public List<Booking> listAllBooking() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking showBookingById(String bookingId) {
		return bookingRepository.findById(bookingId).get();
	}

	@Override
	public void deleteBooking(String bookingId) {
		bookingRepository.deleteById(bookingId);
	}

	@Override
	public void updateBooking(String bookingId, Booking booking) throws ResourceNotFoundException {
		Booking existingBooking = showBookingById(bookingId);
		if (existingBooking != null) {
			existingBooking.setCheckInDate(booking.getCheckInDate());
			existingBooking.setTotalPrice(booking.getTotalPrice());
			existingBooking.setStatus(booking.getStatus());
			bookingRepository.save(existingBooking);
		} else {
			throw new ResourceNotFoundException("Booking not found with ID: " + bookingId);
		}
	}

	/*
	 * @Override public List<Booking> getBookingsByTenant(String tenantId) { return
	 * bookingRepository.findByTenantId(tenantId); }
	 * 
	 * @Override public List<Booking> getBookingsByRoom(String roomId) { return
	 * bookingRepository.findByRoomId(roomId); }
	 * 
	 * @Override public List<Booking> getBookingsByStatus(String status) { return
	 * bookingRepository.findByStatus(status); }
	 * 
	 * @Override public BigDecimal calculateTotalRevenue() { return
	 * bookingRepository.calculateTotalRevenue(); }
	 */
}

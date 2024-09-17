package com.crimsonlogic.hostelmanagementsystem.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.crimsonlogic.hostelmanagementsystem.entity.Booking;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.util.MonthlyRevenue;

public interface BookingService {
	public Booking createBooking(Booking booking);
	public List<Booking> listAllBooking();
	public Booking showBookingById(String bookingId);
	public void deleteBooking(String bookingId);
	public void updateBooking(String bookingId, Booking booking) throws ResourceNotFoundException;
	
	  public List<Booking> getBookingsByTenant(String tenantId); 
	  public List<Booking> getBookingsByRoom(String roomId); 
	  public List<Booking> getBookingsByStatus(String status);
	  public BigDecimal calculateTotalRevenue();
	  public List<MonthlyRevenue> calculateMonthlyRevenue(int year);
	  public Booking confirmBooking(String roomId, String tenantId, Date checkInDate, Date checkOutDate);

}

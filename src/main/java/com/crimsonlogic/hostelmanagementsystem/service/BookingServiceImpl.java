
package com.crimsonlogic.hostelmanagementsystem.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.hostelmanagementsystem.repository.BookingRepository;
import com.crimsonlogic.hostelmanagementsystem.util.MonthlyRevenue;
import com.crimsonlogic.hostelmanagementsystem.entity.Booking;
import com.crimsonlogic.hostelmanagementsystem.entity.Room;
import com.crimsonlogic.hostelmanagementsystem.entity.Tenant;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;

@Service

@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
    private RoomService roomService;

    @Autowired
    private TenantService tenantService;
	
    //Creates room booking
	@Override
	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	//Lists all booking
	@Override
	public List<Booking> listAllBooking() {
		return bookingRepository.findAll();
	}

	//Lists booking by booking id
	@Override
	public Booking showBookingById(String bookingId) {
		return bookingRepository.findById(bookingId).get();
	}

	
	//	Delete booking by booking id
	@Override
	public void deleteBooking(String bookingId) {
		bookingRepository.deleteById(bookingId);
	}

	
	//Update booking by booking id
	@Override
	public void updateBooking(String bookingId, Booking booking) throws ResourceNotFoundException {
		Booking existingBooking = showBookingById(bookingId);
		if (existingBooking != null) {
			existingBooking.setCheckInDate(booking.getCheckInDate());
			existingBooking.setCheckOutDate(booking.getCheckOutDate());
			existingBooking.setTotalPrice(booking.getTotalPrice());
			existingBooking.setStatus(booking.getStatus());
			bookingRepository.save(existingBooking);
		} else {
			throw new ResourceNotFoundException("Booking not found with ID: " + bookingId);
		}
	}
	
	
	//Confirm booking by tenant
	@Override
    public Booking confirmBooking(String roomId, String tenantId, Date checkInDate, Date checkOutDate) {
        Room room = roomService.showRoomById(roomId);
        Tenant tenant = tenantService.showTenantById(tenantId);
        
        // Calculate the number of days stayed
        long diffInMillies = checkOutDate.getTime() - checkInDate.getTime();
        long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);

        // Calculate the total price
        double totalPrice = diffInDays * room.getPrice();

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setTenant(tenant);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setTotalPrice(totalPrice);
        booking.setBookingDate(new Date()); // Set booking date to current date
        booking.setStatus("pending"); // Initial status

        return bookingRepository.save(booking);
    }

	
	  //Show bookings by tenant id
	  @Override public List<Booking> getBookingsByTenant(String tenantId) { return
	  bookingRepository.findByTenant_TenantId(tenantId); }
	  
	  //Show bookings by room id
	  @Override public List<Booking> getBookingsByRoom(String roomId) { return
	  bookingRepository.findByRoom_RoomId(roomId); }
	  
	  //Show bookings by booking status
	  @Override public List<Booking> getBookingsByStatus(String status) { return
	  bookingRepository.findByStatus(status); }
	  
	  //calculate total revenue
	  @Override public BigDecimal calculateTotalRevenue() { return
	  bookingRepository.calculateTotalRevenue(); }
	  
	  
	  //calculate monthly revenue
	  public List<MonthlyRevenue> calculateMonthlyRevenue(int year) {
	        return bookingRepository.calculateMonthlyRevenue(year);
	    }
	 
}

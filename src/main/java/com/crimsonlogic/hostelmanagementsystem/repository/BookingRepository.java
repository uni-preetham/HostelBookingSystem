
package com.crimsonlogic.hostelmanagementsystem.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.hostelmanagementsystem.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

	/*
	 * public List<Booking> findByTenantId(String tenantId);
	 * 
	 * public List<Booking> findByRoomId(String roomId);
	 * 
	 * public List<Booking> findByStatus(String status);
	 * 
	 * @Query("SELECT SUM(b.totalPrice) FROM Booking b") BigDecimal
	 * calculateTotalRevenue();
	 */
}

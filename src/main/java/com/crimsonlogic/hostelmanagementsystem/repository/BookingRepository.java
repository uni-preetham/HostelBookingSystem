
package com.crimsonlogic.hostelmanagementsystem.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.hostelmanagementsystem.entity.Booking;
import com.crimsonlogic.hostelmanagementsystem.util.MonthlyRevenue;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {


	  public List<Booking> findByTenant_TenantId(String tenantId);
	  
	  public List<Booking> findByRoom_RoomId(String roomId);
	  
	  public List<Booking> findByStatus(String status);
	  
	  @Query("SELECT SUM(b.totalPrice) FROM Booking b") BigDecimal
	  calculateTotalRevenue();
	 
	  @Query("SELECT new com.crimsonlogic.hostelmanagementsystem.util.MonthlyRevenue(MONTH(b.bookingDate), SUM(b.totalPrice)) " +
	           "FROM Booking b " +
	           "WHERE YEAR(b.bookingDate) = :year " + 
	           "GROUP BY MONTH(b.bookingDate)")
	    List<MonthlyRevenue> calculateMonthlyRevenue(@Param("year") int year);
}

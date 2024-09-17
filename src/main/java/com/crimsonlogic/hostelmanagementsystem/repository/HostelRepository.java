package com.crimsonlogic.hostelmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.hostelmanagementsystem.entity.Hostel;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, String> {
	List<Hostel> findByHostelLocationContainingIgnoreCase(String location);

	@Query("SELECT h FROM Hostel h WHERE LOWER(h.hostelAmenities) LIKE LOWER(CONCAT('%', :amenity, '%'))")
	List<Hostel> findByHostelAmenitiesContaining(@Param("amenity") String amenity);
}

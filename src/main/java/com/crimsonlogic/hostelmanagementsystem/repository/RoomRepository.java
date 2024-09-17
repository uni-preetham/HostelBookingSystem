package com.crimsonlogic.hostelmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.hostelmanagementsystem.entity.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room, String>{
	List<Room> findByHostel_HostelId(String hostelId);  // This accesses the 'hostelId' field of the 'Hostel' entity through 'hostel'

	List<Room> findByHostelHostelId(String hostelId);

}

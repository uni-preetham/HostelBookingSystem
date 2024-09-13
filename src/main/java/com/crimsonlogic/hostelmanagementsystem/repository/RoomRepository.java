package com.crimsonlogic.hostelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.hostelmanagementsystem.entity.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room, String>{

}

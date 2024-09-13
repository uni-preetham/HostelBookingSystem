package com.crimsonlogic.hostelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.hostelmanagementsystem.entity.Hostel;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, String>{

}

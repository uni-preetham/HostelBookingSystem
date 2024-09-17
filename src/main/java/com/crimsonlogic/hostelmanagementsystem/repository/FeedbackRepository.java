package com.crimsonlogic.hostelmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crimsonlogic.hostelmanagementsystem.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, String>{
	public List<Feedback> findByTenantId(String tenantId);
}

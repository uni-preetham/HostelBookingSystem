package com.crimsonlogic.hostelmanagementsystem.service;

import java.util.List;

import com.crimsonlogic.hostelmanagementsystem.entity.Feedback;

public interface FeedbackService {
	public List<Feedback> getAllFeedback();
	public Feedback submitFeedback(String tenantId, String description);
	public void updateFeedbackStatus(String feedbackId, String status);
}

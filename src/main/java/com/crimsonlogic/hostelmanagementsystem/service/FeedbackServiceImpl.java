package com.crimsonlogic.hostelmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.hostelmanagementsystem.entity.Feedback;
import com.crimsonlogic.hostelmanagementsystem.repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback submitFeedback(String tenantId, String description) {
        Feedback feedback = new Feedback(tenantId, description);
        return feedbackRepository.save(feedback);
    }

    public void updateFeedbackStatus(String feedbackId, String status) {
        Feedback feedback= feedbackRepository.findById(feedbackId).get();
        feedback.setFeedbackStatus(status);
        feedbackRepository.save(feedback);
    }

	
}
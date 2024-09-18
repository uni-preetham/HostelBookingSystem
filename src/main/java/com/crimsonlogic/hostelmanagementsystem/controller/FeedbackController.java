package com.crimsonlogic.hostelmanagementsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crimsonlogic.hostelmanagementsystem.entity.Feedback;
import com.crimsonlogic.hostelmanagementsystem.service.FeedbackService;


/**
 * Represents feedback in the hostel management system.
 * Author: Preetham A A
 */

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

	private static final Logger LOG = LoggerFactory.getLogger(FeedbackController.class);

	@Autowired
	private FeedbackService feedbackService;

	
	/*
	 * Displays the feedback form.
	 * 
	 */
	@GetMapping("/showfeedbackform")
	public String showFeedbackForm(Model model) {
		LOG.debug("inside showFeedbackForm handler method");
		model.addAttribute("feedback", new Feedback());
		return "feedbackform";
	}

	/*
	 * Submit the feedback form.
	 * 
	 */
	@PostMapping("/submit")
	public String submitFeedback(@RequestParam("tenantId") String tenantId,
			@RequestParam("description") String description, Model model) {
		LOG.debug("inside submitFeedback handler method");
		feedbackService.submitFeedback(tenantId, description);
		model.addAttribute("message", "Feedback sent successfully");
		return "tenantdashboard";
	}
	
	/*
	 * View the feedback in admin.
	 * 
	 */
	@GetMapping("/viewfeedback")
	public String viewComplaints(Model model) {
		List<Feedback> feedbacks = feedbackService.getAllFeedback();
		model.addAttribute("feedbacks", feedbacks);
		return "feedbacklist";
	}

	/*
	 * Update the status in feedback list.
	 * 
	 */
	@PostMapping("/update-status/{feedbackId}")
	public String updateFeedbackStatus(@PathVariable String feedbackId, @RequestParam("status") String status) {
		feedbackService.updateFeedbackStatus(feedbackId, status);
		return "redirect:/manager/dashboard";
	}
}
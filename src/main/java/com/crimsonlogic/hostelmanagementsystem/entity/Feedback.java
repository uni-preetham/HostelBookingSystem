package com.crimsonlogic.hostelmanagementsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a booking in the hostel management system.
 * Author: Preetham A A
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@Column(name = "feedback_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id-generator")
	@GenericGenerator(name = "custom-id-generator", strategy = "com.crimsonlogic.hostelmanagementsystem.util.FeedbackIdGenerator")
	private String feedbackId;

	@Column(name = "tenant_id")
	private String tenantId;

	@Column(name = "feedback_description")
	private String feedbackDescription;

	@Column(name = "feedback_status")
	private String feedbackStatus; // e.g., "Pending", "Resolved"

	@Column(name = "created_at")
	private Date createdAt;

	@ManyToOne
	@JoinColumn(name = "tenant_id", insertable = false, updatable = false)
	private Tenant tenant;

	public Feedback(String tenantId, String feedbackDescription) {
		this.tenantId = tenantId;
		this.feedbackDescription = feedbackDescription;
		this.feedbackStatus = "Pending";
		this.createdAt = new Date();
	}

}

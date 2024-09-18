package com.crimsonlogic.hostelmanagementsystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * Represents a booking in the hostel management system.
 * Author: Preetham A A
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "hostels")
public class Hostel {
    @Id
    @Column(name = "hostel_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id-generator")
	@GenericGenerator(name = "custom-id-generator", strategy = "com.crimsonlogic.hostelmanagementsystem.util.HostelIdGenerator")
    private String hostelId;

    @Column(name = "hostel_name")
    private String hostelName;

    @Column(name = "hostel_location")
    private String hostelLocation;

    @Column(name = "hostel_description")
    private String hostelDescription;

    @Column(name = "hostel_amenities")
    private String hostelAmenities;
}

package com.crimsonlogic.hostelmanagementsystem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.hostelmanagementsystem.entity.Hostel;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.repository.HostelRepository;

@Service
@Transactional
public class HostelServiceImpl implements HostelService {

	@Autowired
	private HostelRepository hostelRepository;

	
	//Add a hostel
	@Override
	public Hostel addHostel(Hostel hostel) {
		return hostelRepository.save(hostel);
	}

	
	//Show all hostels
	@Override
	public List<Hostel> listAllHostels() {
		return hostelRepository.findAll();
	}

	
	//Show hostel by hostel id
	@Override
	public Hostel showHostelById(String hostelId) throws ResourceNotFoundException {
		return hostelRepository.findById(hostelId).get();
	}

	
	//update hostel by hostel id
	@Override
	public void updateHostel(String hostelId, Hostel hostelDetails) throws ResourceNotFoundException {
		Hostel existingHostel = showHostelById(hostelId);
		if (existingHostel != null) {
			existingHostel.setHostelName(hostelDetails.getHostelName());
			existingHostel.setHostelLocation(hostelDetails.getHostelLocation());
			existingHostel.setHostelDescription(hostelDetails.getHostelDescription());
			existingHostel.setHostelAmenities(hostelDetails.getHostelAmenities());
			hostelRepository.save(existingHostel);
		} else {
			throw new ResourceNotFoundException("Hostel not found with ID: " + hostelId);
		}
	}

	
	//delete hostel by hostel id
	@Override
	public void deleteHostel(String hostelId) throws ResourceNotFoundException {
		Hostel hostel = showHostelById(hostelId);
		if (hostel != null) {
			hostelRepository.deleteById(hostelId);
		} else {
			throw new ResourceNotFoundException("Hostel not found with ID: " + hostelId);
		}
	}

	
	//Search for hostels by location
	public List<Hostel> searchHostelsByLocation(String location) {
		return hostelRepository.findByHostelLocationContainingIgnoreCase(location);
	}

	
	//Fetch a hostel by hostel Id
	@Override
	public Hostel getHostelById(String hostelId) throws ResourceNotFoundException {
		return hostelRepository.findById(hostelId).orElseThrow(() -> new ResourceNotFoundException("Hostel not found"));
	}

	
	//Fetch hostels by amenities
	@Override
	public List<Hostel> getHostelsByAmenities(List<String> amenities) {
		if (amenities == null || amenities.isEmpty()) {
			return listAllHostels();
		}
		// Start with the first amenity and filter through the rest
		List<Hostel> filteredHostels = hostelRepository.findByHostelAmenitiesContaining(amenities.get(0));
		for (int i = 1; i < amenities.size(); i++) {
			List<Hostel> filteredByCurrentAmenity = hostelRepository.findByHostelAmenitiesContaining(amenities.get(i));
			filteredHostels.retainAll(filteredByCurrentAmenity);
		}
		return filteredHostels;
	}
}

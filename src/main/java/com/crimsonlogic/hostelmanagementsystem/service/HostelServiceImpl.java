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

    @Override
    public Hostel addHostel(Hostel hostel) {
        return hostelRepository.save(hostel);
    }

    @Override
    public List<Hostel> listAllHostels() {
        return hostelRepository.findAll();
    }

    @Override
    public Hostel showHostelById(String hostelId) throws ResourceNotFoundException {
        return hostelRepository.findById(hostelId).get();
    }

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

    @Override
    public void deleteHostel(String hostelId) throws ResourceNotFoundException {
        Hostel hostel = showHostelById(hostelId);
        if (hostel != null) {
            hostelRepository.deleteById(hostelId);
        } else {
            throw new ResourceNotFoundException("Hostel not found with ID: " + hostelId);
        }
    }
    
    public List<Hostel> searchHostelsByLocation(String location) {
        return hostelRepository.findByHostelLocationContainingIgnoreCase(location);
    }

    
    @Override
    public Hostel getHostelById(String hostelId) throws ResourceNotFoundException {
        return hostelRepository.findById(hostelId)
                               .orElseThrow(() -> new ResourceNotFoundException("Hostel not found"));
    }

}

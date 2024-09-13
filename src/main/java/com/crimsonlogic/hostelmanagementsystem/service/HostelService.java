package com.crimsonlogic.hostelmanagementsystem.service;

import java.util.List;

import com.crimsonlogic.hostelmanagementsystem.entity.Hostel;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;

public interface HostelService {
    public Hostel addHostel(Hostel hostel);
    public List<Hostel> listAllHostels();
    public Hostel showHostelById(String hostelId) throws ResourceNotFoundException;
    public void updateHostel(String hostelId, Hostel hostel) throws ResourceNotFoundException;
    public void deleteHostel(String hostelId) throws ResourceNotFoundException;
}

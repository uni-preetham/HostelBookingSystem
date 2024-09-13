package com.crimsonlogic.hostelmanagementsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crimsonlogic.hostelmanagementsystem.entity.Hostel;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.service.HostelService;

@Controller
@RequestMapping("/hostel")
public class HostelController {
	private static final Logger LOG = LoggerFactory.getLogger(TenantController.class);
	
    @Autowired
    private HostelService hostelService;
    
    @GetMapping("/showHostelForm")
    public String showHostelForm(Model model) {
    	LOG.debug("inside showHostelForm handler method");
        model.addAttribute("hostel", new Hostel());  // Bind an empty tenant object for form
        return "registerhostel";  // Return the name of the form (HTML or JSP)
    }

    // Create a new hostel
    @PostMapping("/createhostel")
    public String createHostel(@ModelAttribute("hostel") Hostel hostel, Model model) {
    	LOG.debug("inside createHostel handler method");
    	hostelService.addHostel(hostel);
    	model.addAttribute("tenants", hostelService.listAllHostels());
        return "redirect:/tenant/listalltenant";
    }

    // Get a list of all hostels
    @GetMapping("/listhostels")
    public List<Hostel> listAllHostels() {
        return hostelService.listAllHostels();
    }

    // Get a specific hostel by ID
    @GetMapping("/showhostelbyid/{hostelId}")
    public Hostel showHostelById(@PathVariable("hostelId") String hostelId) throws ResourceNotFoundException {
        return hostelService.showHostelById(hostelId);
    }

    // Update an existing hostel by ID
    @PutMapping("/updatehostel/{hostelId}")
    public void updateHostel(@PathVariable("hostelId") String hostelId, @RequestBody Hostel hostelDetails) throws ResourceNotFoundException {
        hostelService.updateHostel(hostelId, hostelDetails);
    }

    // Delete a hostel by ID
    @DeleteMapping("/deletehostel/{hostelId}")
    public void deleteHostel(@PathVariable("hostelId") String hostelId) throws ResourceNotFoundException {
        hostelService.deleteHostel(hostelId);
    }
}

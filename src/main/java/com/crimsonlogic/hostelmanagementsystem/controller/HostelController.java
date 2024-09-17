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
import org.springframework.web.bind.annotation.RequestParam;

import com.crimsonlogic.hostelmanagementsystem.entity.Hostel;
import com.crimsonlogic.hostelmanagementsystem.entity.Room;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.service.HostelService;
import com.crimsonlogic.hostelmanagementsystem.service.RoomService;

@Controller
@RequestMapping("/hostel")
public class HostelController {
	private static final Logger LOG = LoggerFactory.getLogger(HostelController.class);
	
    @Autowired
    private HostelService hostelService;
    
    @Autowired
    private RoomService roomService;

    @GetMapping("/showHostelForm")
    public String showHostelForm(Model model) {
    	LOG.debug("inside showHostelForm handler method");
        model.addAttribute("hostel", new Hostel()); 
        return "registerhostel"; 
    }

    // Create a new hostel
    @PostMapping("/createhostel")
    public String createHostel(@ModelAttribute("hostel") Hostel hostel, Model model) {
    	LOG.debug("inside createHostel handler method");
    	hostelService.addHostel(hostel);
//    	model.addAttribute("tenants", hostelService.listAllHostels());
    	model.addAttribute("message", "Hostel added successfully.");
        return "managerdashboard";
    }

    // Get a list of all hostels
    @GetMapping("/listhostels")
    public List<Hostel> listAllHostels() {
        return hostelService.listAllHostels();
    }
    
    @GetMapping("/showhostelbyid/{hostelId}")
    public String showHostelDetails(@PathVariable("hostelId") String hostelId, Model model) throws ResourceNotFoundException {
        Hostel hostel = hostelService.getHostelById(hostelId);
        List<Room> rooms = roomService.getRoomsByHostel(hostelId);
        model.addAttribute("hostel", hostel);
        model.addAttribute("rooms", rooms);
        return "hosteldetails";  
    }
    
    
    @GetMapping("/search")
    public String searchHostelsByLocation(@RequestParam("query") String query, Model model) {
        List<Hostel> hostels = hostelService.searchHostelsByLocation(query);
        model.addAttribute("hostellist", hostels);
        return "searchresults"; // This can be a partial view to update the dropdown dynamically
    }

    
    
    
    @GetMapping("/editHostel")
    public String showEditHostelForm(
            @RequestParam(value = "hostelId", required = false) String hostelId,
            Model model) throws ResourceNotFoundException {

        // Fetch all hostels to populate the dropdown
        List<Hostel> hostels = hostelService.listAllHostels();
        model.addAttribute("hostels", hostels);

        // If a hostelId is provided, fetch the details of the selected hostel
        if (hostelId != null && !hostelId.isEmpty()) {
            Hostel selectedHostel = hostelService.getHostelById(hostelId);
            model.addAttribute("selectedHostel", selectedHostel);
        }

        return "edithostelform";
    }


    // Update an existing hostel
    @PostMapping("/updateHostel")
    public String updateHostel(@ModelAttribute("hostel") Hostel hostel, Model model) throws ResourceNotFoundException {
        hostelService.updateHostel(hostel.getHostelId(), hostel);
        model.addAttribute("message", "Hostel updated successfully.");
        return "managerdashboard"; // Redirect to the dashboard or another appropriate page
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

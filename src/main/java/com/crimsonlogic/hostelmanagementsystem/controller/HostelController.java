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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crimsonlogic.hostelmanagementsystem.entity.Hostel;
import com.crimsonlogic.hostelmanagementsystem.entity.Room;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.service.HostelService;
import com.crimsonlogic.hostelmanagementsystem.service.RoomService;


/**
 * Represents a booking in the hostel management system.
 * Author: Preetham A A
 */

@Controller
@RequestMapping("/hostel")
public class HostelController {
	private static final Logger LOG = LoggerFactory.getLogger(HostelController.class);
	
    @Autowired
    private HostelService hostelService;
    
    @Autowired
    private RoomService roomService;

    
    /*
	 * Displays the hostel form.
	 * 
	 */
    @GetMapping("/showHostelForm")
    public String showHostelForm(Model model) {
    	LOG.debug("inside showHostelForm handler method");
        model.addAttribute("hostel", new Hostel()); 
        return "registerhostel"; 
    }

    
    /*
	 * Creates a hostel in manager dashboard.
	 * 
	 */
    @PostMapping("/createhostel")
    public String createHostel(@ModelAttribute("hostel") Hostel hostel, Model model) {
    	LOG.debug("inside createHostel handler method");
    	hostelService.addHostel(hostel);
    	model.addAttribute("message", "Hostel added successfully.");
        return "managerdashboard";
    }

    
    /*
	 * Displays the hostels present.
	 * 
	 */
    @GetMapping("/listhostels")
    public List<Hostel> listAllHostels() {
        return hostelService.listAllHostels();
    }
    
    
    /*
	 * Displays the hostel by id.
	 * 
	 */
    @GetMapping("/showhostelbyid/{hostelId}")
    public String showHostelDetails(@PathVariable("hostelId") String hostelId, Model model) throws ResourceNotFoundException {
        Hostel hostel = hostelService.getHostelById(hostelId);
        List<Room> rooms = roomService.getRoomsByHostel(hostelId);
        model.addAttribute("hostel", hostel);
        model.addAttribute("rooms", rooms);
        return "hosteldetails";  
    }
    
    
    /*
	 * Search for hostel by location.
	 * 
	 */
    @GetMapping("/search")
    public String searchHostelsByLocation(@RequestParam("query") String query, Model model) {
        List<Hostel> hostels = hostelService.searchHostelsByLocation(query);
        model.addAttribute("hostellist", hostels);
        return "searchresults"; // This can be a partial view to update the dropdown dynamically
    }
    
    
    /*
	 * Displays the hostel edit form.
	 * 
	 */
    @GetMapping("/editHostel")
    public String showEditHostelForm(
            @RequestParam(value = "hostelId", required = false) String hostelId,
            Model model) throws ResourceNotFoundException {
        List<Hostel> hostels = hostelService.listAllHostels();
        model.addAttribute("hostels", hostels);
        if (hostelId != null && !hostelId.isEmpty()) {
            Hostel selectedHostel = hostelService.getHostelById(hostelId);
            model.addAttribute("selectedHostel", selectedHostel);
        }

        return "edithostelform";
    }
    
    
    /*
	 * Updates the hostel.
	 * 
	 */
    @PostMapping("/updateHostel")
    public String updateHostel(@ModelAttribute("hostel") Hostel hostel, Model model) throws ResourceNotFoundException {
        hostelService.updateHostel(hostel.getHostelId(), hostel);
        model.addAttribute("message", "Hostel updated successfully.");
        return "managerdashboard";
    }
    
    
    /*
	 * Filter hostels by amenities.
	 * 
	 */
    @GetMapping("/filterhostels")
    public String filterHostelsByAmenities(
            @RequestParam(value = "amenities", required = false) List<String> amenities,
            Model model) {
        List<Hostel> filteredHostels = hostelService.getHostelsByAmenities(amenities);
        model.addAttribute("hostellist", filteredHostels);
        model.addAttribute("selectedAmenities", amenities);
        return "hostelslist";  // Your JSP page that lists the hostels
    }
}

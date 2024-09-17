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
@RequestMapping("/room")
public class RoomController {

	private static final Logger LOG = LoggerFactory.getLogger(RoomController.class);
	
	@Autowired
	private RoomService roomService;

	@Autowired
	private HostelService hostelService;

	@GetMapping("/showroomform")
	public String showRoomForm(Model model) {
		
		LOG.debug("inside showRoomForm handler method");
		// Assuming you have a HostelService to get all hostels
		List<Hostel> hostels = hostelService.listAllHostels();
		model.addAttribute("hostels", hostels);
		return "addroom"; // Name of the JSP page that contains the room form
	}

	// Create a new room
	@PostMapping("/createroom")
	public String createRoom(@RequestParam("roomNumber") String roomNumber,
			@RequestParam("roomFloorNumber") Integer roomFloorNumber, @RequestParam("roomType") String roomType,
			@RequestParam("price") Double price, @RequestParam(value = "availability", required = false) Boolean availability,
			@RequestParam("hostelId") String hostelId, Model model) throws ResourceNotFoundException {
		LOG.debug("inside createRoom handler method");
		
		// Default value if availability is not provided
	    if (availability == null) {
	        availability = false;
	    }

		Hostel hostel = hostelService.showHostelById(hostelId); // Fetch selected hostel
		Room room = new Room();
		room.setRoomNumber(roomNumber);
		room.setRoomFloorNumber(roomFloorNumber);
		room.setRoomType(roomType);
		room.setPrice(price);
		room.setAvailability(availability);
		room.setHostel(hostel); // Set hostel for the room

		roomService.addRoom(room);
		model.addAttribute("message", "Room added successfully");
		return "managerdashboard"; // Redirect to list all rooms after creation
	}

	/*
	 * @PostMapping("/createroom") public Room createRoom(@RequestBody Room room) {
	 * return roomService.addRoom(room); }
	 */

	// Get a list of all rooms
	@GetMapping("/listrooms")
	public String listAllRooms(Model model) {
		List<Room> rooms = roomService.listAllRooms();
		model.addAttribute("rooms", rooms);
		return "roomlist";
	}

	// Get a specific room by ID
	@GetMapping("/showroombyid/{roomId}")
	public Room showRoomById(@PathVariable("roomId") String roomId) throws ResourceNotFoundException {
		return roomService.showRoomById(roomId);
	}

	// Update an existing room by ID
	@PutMapping("/updateroom/{roomId}")
	public void updateRoom(@PathVariable("roomId") String roomId, @RequestBody Room roomDetails)
			throws ResourceNotFoundException {
		roomService.updateRoom(roomId, roomDetails);
	}

	// Delete a room by ID
	@DeleteMapping("/deleteroom/{roomId}")
	public void deleteRoom(@PathVariable("roomId") String roomId) throws ResourceNotFoundException {
		roomService.deleteRoom(roomId);
	}
	
	// Show the edit room form with hostels and optionally rooms
	@GetMapping("/editroomform")
	public String showEditRoomForm(@RequestParam(value = "hostelId", required = false) String hostelId,
	                               @RequestParam(value = "roomId", required = false) String roomId, Model model) {
	    // Get list of hostels for the first dropdown
	    List<Hostel> hostels = hostelService.listAllHostels();
	    model.addAttribute("hostels", hostels);

	    // If hostelId is selected, fetch rooms for that hostel
	    if (hostelId != null && !hostelId.isEmpty()) {
	        List<Room> rooms = roomService.findRoomsByHostelId(hostelId);
	        model.addAttribute("rooms", rooms);
	        model.addAttribute("selectedHostelId", hostelId);
	    }

	    // If roomId is selected, fetch the room details
	    if (roomId != null && !roomId.isEmpty()) {
	        Room room = roomService.showRoomById(roomId);
	        model.addAttribute("selectedRoom", room);
	    }

	    return "editroomform"; // Return JSP page
	}
	
	@PostMapping("/updateroom")
	public String updateRoom(
	        @RequestParam("roomId") String roomId,
	        @RequestParam("roomNumber") String roomNumber,
	        @RequestParam("roomFloorNumber") Integer roomFloorNumber,
	        @RequestParam("roomType") String roomType,
	        @RequestParam("price") Double price,
	        @RequestParam(value = "availability", required = false) Boolean availability, Model model) throws ResourceNotFoundException {

	    // Set availability to false if it is null (unchecked checkbox)
	    if (availability == null) {
	        availability = false;
	    }

	    // Fetch and update the room
	    Room room = roomService.showRoomById(roomId);
	    room.setRoomNumber(roomNumber);
	    room.setRoomFloorNumber(roomFloorNumber);
	    room.setRoomType(roomType);
	    room.setPrice(price);
	    room.setAvailability(availability);

	    roomService.updateRoom(roomId, room);
	    
	    model.addAttribute("message", "Room updated successfully");
		return "managerdashboard";

	    
	}


}

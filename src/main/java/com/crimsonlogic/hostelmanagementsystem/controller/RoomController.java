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

import com.crimsonlogic.hostelmanagementsystem.entity.Hostel;
import com.crimsonlogic.hostelmanagementsystem.entity.Room;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.service.HostelService;
import com.crimsonlogic.hostelmanagementsystem.service.RoomService;

/**
 * Represents a booking in the hostel management system. Author: Preetham A A
 */

@Controller
@RequestMapping("/room")
public class RoomController {

	private static final Logger LOG = LoggerFactory.getLogger(RoomController.class);

	@Autowired
	private RoomService roomService;

	@Autowired
	private HostelService hostelService;

	/*
	 * Displays the add room form.
	 * 
	 */
	@GetMapping("/showroomform")
	public String showRoomForm(Model model) {

		LOG.debug("inside showRoomForm handler method");
		List<Hostel> hostels = hostelService.listAllHostels();
		model.addAttribute("hostels", hostels);
		return "addroom";
	}

	/*
	 * Create a new room.
	 * 
	 */
	@PostMapping("/createroom")
	public String createRoom(@RequestParam("roomNumber") String roomNumber,
			@RequestParam("roomFloorNumber") Integer roomFloorNumber, @RequestParam("roomType") String roomType,
			@RequestParam("price") Double price,
			@RequestParam(value = "availability", required = false) Boolean availability,
			@RequestParam("hostelId") String hostelId, Model model) throws ResourceNotFoundException {
		LOG.debug("inside createRoom handler method");
		if (availability == null) {
			availability = false;
		}

		Hostel hostel = hostelService.showHostelById(hostelId);
		Room room = new Room();
		room.setRoomNumber(roomNumber);
		room.setRoomFloorNumber(roomFloorNumber);
		room.setRoomType(roomType);
		room.setPrice(price);
		room.setAvailability(availability);
		room.setHostel(hostel);

		roomService.addRoom(room);
		model.addAttribute("message", "Room added successfully");
		return "managerdashboard";
	}

	/*
	 * Displays the all rooms.
	 * 
	 */
	@GetMapping("/listrooms")
	public String listAllRooms(Model model) {
		List<Room> rooms = roomService.listAllRooms();
		model.addAttribute("rooms", rooms);
		return "roomlist";
	}

	/*
	 * Displays room by room id.
	 * 
	 */
	@GetMapping("/showroombyid/{roomId}")
	public Room showRoomById(@PathVariable("roomId") String roomId) throws ResourceNotFoundException {
		return roomService.showRoomById(roomId);
	}

	/*
	 * Displays the edit room form.
	 * 
	 */
	@GetMapping("/editroomform")
	public String showEditRoomForm(@RequestParam(value = "hostelId", required = false) String hostelId,
			@RequestParam(value = "roomId", required = false) String roomId, Model model) {
		List<Hostel> hostels = hostelService.listAllHostels();
		model.addAttribute("hostels", hostels);

		if (hostelId != null && !hostelId.isEmpty()) {
			List<Room> rooms = roomService.findRoomsByHostelId(hostelId);
			model.addAttribute("rooms", rooms);
			model.addAttribute("selectedHostelId", hostelId);
		}

		if (roomId != null && !roomId.isEmpty()) {
			Room room = roomService.showRoomById(roomId);
			model.addAttribute("selectedRoom", room);
		}

		return "editroomform";
	}

	/*
	 * Update the room details.
	 * 
	 */
	@PostMapping("/updateroom")
	public String updateRoom(@RequestParam("roomId") String roomId, @RequestParam("roomNumber") String roomNumber,
			@RequestParam("roomFloorNumber") Integer roomFloorNumber, @RequestParam("roomType") String roomType,
			@RequestParam("price") Double price,
			@RequestParam(value = "availability", required = false) Boolean availability, Model model)
			throws ResourceNotFoundException {
		if (availability == null) {
			availability = false;
		}
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

package com.crimsonlogic.hostelmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.hostelmanagementsystem.entity.Room;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.service.RoomService;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Create a new room
    @PostMapping("/createroom")
    public Room createRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    // Get a list of all rooms
    @GetMapping("/listrooms")
    public List<Room> listAllRooms() {
        return roomService.listAllRooms();
    }

    // Get a specific room by ID
    @GetMapping("/showroombyid/{roomId}")
    public Room showRoomById(@PathVariable("roomId") String roomId) throws ResourceNotFoundException {
        return roomService.showRoomById(roomId);
    }

    // Update an existing room by ID
    @PutMapping("/updateroom/{roomId}")
    public void updateRoom(@PathVariable("roomId") String roomId, @RequestBody Room roomDetails) throws ResourceNotFoundException {
        roomService.updateRoom(roomId, roomDetails);
    }

    // Delete a room by ID
    @DeleteMapping("/deleteroom/{roomId}")
    public void deleteRoom(@PathVariable("roomId") String roomId) throws ResourceNotFoundException {
        roomService.deleteRoom(roomId);
    }
}

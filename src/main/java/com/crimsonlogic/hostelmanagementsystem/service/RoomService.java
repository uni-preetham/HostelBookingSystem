package com.crimsonlogic.hostelmanagementsystem.service;

import java.util.List;

import com.crimsonlogic.hostelmanagementsystem.entity.Room;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;

public interface RoomService {
    public Room addRoom(Room room);
    public List<Room> listAllRooms();
    public Room showRoomById(String roomId);
    public void deleteRoom(String roomId);
    public void updateRoom(String roomId, Room room) throws ResourceNotFoundException;
}

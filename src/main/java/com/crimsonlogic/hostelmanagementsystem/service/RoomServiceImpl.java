package com.crimsonlogic.hostelmanagementsystem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.hostelmanagementsystem.entity.Room;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.repository.RoomRepository;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    
    //Add room
    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    
    //List all rooms
    @Override
    public List<Room> listAllRooms() {
        return roomRepository.findAll();
    }

    
    //List room by room id
    @Override
    public Room showRoomById(String roomId) {
        return roomRepository.findById(roomId).get();
    }

    
    //Delete room by room id
    @Override
    public void deleteRoom(String roomId) {
        Room room = showRoomById(roomId);
        if (room != null) {
            roomRepository.deleteById(roomId);
        }
    }

    
    //Update room by room id
    @Override
    public void updateRoom(String roomId, Room room) throws ResourceNotFoundException {
        Room existingRoom = showRoomById(roomId);
        if (existingRoom != null) {
            existingRoom.setRoomNumber(room.getRoomNumber());
            existingRoom.setRoomFloorNumber(room.getRoomFloorNumber());
            existingRoom.setRoomType(room.getRoomType());
            existingRoom.setPrice(room.getPrice());
            existingRoom.setAvailability(room.getAvailability());
            existingRoom.setHostel(room.getHostel());
            roomRepository.save(existingRoom);
        } else {
            throw new ResourceNotFoundException("Room not found");
        }
    }
    
    
    //Show rooms by hostel id
    @Override
    public List<Room> getRoomsByHostel(String hostelId) {
        return roomRepository.findByHostel_HostelId(hostelId);  // Custom query to find rooms by hostel
    }
    
    //Find rooms by hostel ID
    @Override
    public List<Room> findRoomsByHostelId(String hostelId) {
        return roomRepository.findByHostelHostelId(hostelId);
    }

}

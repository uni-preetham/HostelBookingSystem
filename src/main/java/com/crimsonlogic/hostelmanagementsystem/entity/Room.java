package com.crimsonlogic.hostelmanagementsystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id-generator")
	@GenericGenerator(name = "custom-id-generator", strategy = "com.crimsonlogic.hostelmanagementsystem.util.RoomIdGenerator")
    private String roomId;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "room_floor_number")
    private Integer roomFloorNumber;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "price")
    private Double price;

    @Column(name = "availability")
    private Boolean availability = true;
    
    @ManyToOne
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;
}

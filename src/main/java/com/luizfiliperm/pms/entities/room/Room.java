package com.luizfiliperm.pms.entities.room;

import com.luizfiliperm.pms.entities.property.Property;
import com.luizfiliperm.pms.entities.room.enums.RoomStatus;
import com.luizfiliperm.pms.entities.room.enums.RoomType;
import jakarta.persistence.*;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "description")
    private String description;

    @Column(name = "room_status")
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @Column(name = "room_type")
    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Column(name = "floor")
    private Integer floor;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

}

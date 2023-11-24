package com.luizfiliperm.pms.dtos.room;

import com.luizfiliperm.pms.entities.room.Room;
import com.luizfiliperm.pms.entities.room.enums.RoomStatus;
import com.luizfiliperm.pms.entities.room.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDtoResponse {
    private Long id;

    private Integer number;

    private String description;

    private RoomStatus status;

    private RoomType type;

    private Integer floor;

    public RoomDtoResponse(Room room){
        this.id = room.getId();
        this.number = room.getNumber();
        this.description = room.getDescription();
        this.status = room.getStatus();
        this.type = room.getType();
        this.floor = room.getFloor();
    }

    public Room convertToRoom(){
        Room room = new Room();
        room.setId(this.id);
        room.setNumber(this.number);
        room.setDescription(this.description);
        room.setStatus(this.status);
        room.setType(this.type);
        room.setFloor(this.floor);
        return room;
    }
}

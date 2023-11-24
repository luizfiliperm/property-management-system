package com.luizfiliperm.pms.dtos.room;

import com.luizfiliperm.pms.entities.room.Room;
import com.luizfiliperm.pms.entities.room.enums.RoomStatus;
import com.luizfiliperm.pms.entities.room.enums.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDtoReceive {

    @NotNull(message = "The number is required")
    private Integer number;

    private String description;

    private RoomStatus status;

    private RoomType type;

    @NotNull(message = "The floor is required")
    private Integer floor;

    public RoomDtoReceive(Room room){
        this.number = room.getNumber();
        this.description = room.getDescription();
        this.status = room.getStatus();
        this.type = room.getType();
        this.floor = room.getFloor();
    }

    public Room convertToRoom(){
        Room room = new Room();
        room.setNumber(this.number);
        room.setDescription(this.description);
        room.setStatus(this.status);
        room.setType(this.type);
        room.setFloor(this.floor);
        return room;
    }
}

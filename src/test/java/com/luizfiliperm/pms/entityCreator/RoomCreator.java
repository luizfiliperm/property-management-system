package com.luizfiliperm.pms.entityCreator;

import com.luizfiliperm.pms.dtos.room.RoomDtoReceive;
import com.luizfiliperm.pms.dtos.room.RoomDtoResponse;
import com.luizfiliperm.pms.entities.room.enums.RoomStatus;
import com.luizfiliperm.pms.entities.room.enums.RoomType;

public class RoomCreator {
    public static RoomDtoReceive getRoomReceive(){
        return new RoomDtoReceive(1, "description", RoomStatus.AVAILABLE, RoomType.DOUBLE, 1);
    }
}

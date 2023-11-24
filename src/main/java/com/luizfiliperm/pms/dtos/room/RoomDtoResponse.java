package com.luizfiliperm.pms.dtos.room;

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

    private Long propertyId;
}

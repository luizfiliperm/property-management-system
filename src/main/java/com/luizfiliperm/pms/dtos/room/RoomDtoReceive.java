package com.luizfiliperm.pms.dtos.room;

import com.luizfiliperm.pms.entities.room.enums.RoomStatus;
import com.luizfiliperm.pms.entities.room.enums.RoomType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDtoReceive {

    @NotBlank(message = "The number is required")
    private Integer number;

    private String description;

    @NotBlank(message = "The status is required")
    private RoomStatus status;

    @NotBlank(message = "The type is required")
    private RoomType type;

    @NotBlank(message = "The floor is required")
    private Integer floor;
}

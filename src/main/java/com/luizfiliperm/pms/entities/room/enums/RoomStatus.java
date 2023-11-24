package com.luizfiliperm.pms.entities.room.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoomStatus {
    AVAILABLE("available"),
    UNAVAILABLE("unavailable"),
    MAINTENANCE("maintenance");

    private String status;


}

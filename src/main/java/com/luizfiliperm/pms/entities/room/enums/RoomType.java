package com.luizfiliperm.pms.entities.room.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoomType {
    SINGLE("single"),
    DOUBLE("double"),
    TRIPLE("triple"),
    QUADRUPLE("quadruple"),
    SUITE("suite");

    private String type;

}

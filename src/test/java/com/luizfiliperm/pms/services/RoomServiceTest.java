package com.luizfiliperm.pms.services;

import com.luizfiliperm.pms.dtos.room.RoomDtoReceive;
import com.luizfiliperm.pms.dtos.room.RoomDtoResponse;
import com.luizfiliperm.pms.entityCreator.PropertyCreator;
import com.luizfiliperm.pms.entityCreator.RoomCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class RoomServiceTest {

    @Autowired
    RoomService roomService;

    @Autowired
    PropertyService propertyService;

    @Test
    public void testRegister(){
        Long propertyId = propertyService.save(PropertyCreator.getPropertyReceive()).getId();
        RoomDtoReceive roomDtoReceive = RoomCreator.getRoomReceive();

        RoomDtoResponse savedRoom = roomService.save(roomDtoReceive, propertyId);

        assertNotNull(savedRoom);
        assertEquals(roomDtoReceive.getDescription(), savedRoom.getDescription());
        assertEquals(roomDtoReceive.getStatus(), savedRoom.getStatus());
        assertEquals(roomDtoReceive.getType(), savedRoom.getType());
        assertEquals(roomDtoReceive.getNumber(), savedRoom.getNumber());
        assertEquals(roomDtoReceive.getFloor(), savedRoom.getFloor());

    }

    @Test
    public void testFindByIdAndPropertyId(){
        Long propertyId = propertyService.save(PropertyCreator.getPropertyReceive()).getId();
        RoomDtoReceive room = RoomCreator.getRoomReceive();
        Long roomId = roomService.save(RoomCreator.getRoomReceive(), propertyId).getId();

        RoomDtoResponse savedRoom = roomService.findByIdAndPropertyId(roomId, propertyId);

        assertNotNull(savedRoom);
        assertEquals(1L, savedRoom.getId());
        assertEquals(room.getDescription(), savedRoom.getDescription());
        assertEquals(room.getStatus(), savedRoom.getStatus());
        assertEquals(room.getType(), savedRoom.getType());
        assertEquals(room.getNumber(), savedRoom.getNumber());
        assertEquals(room.getFloor(), savedRoom.getFloor());
    }
}

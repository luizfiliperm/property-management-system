package com.luizfiliperm.pms.controllers;

import com.luizfiliperm.pms.dtos.room.RoomDtoReceive;
import com.luizfiliperm.pms.dtos.room.RoomDtoResponse;
import com.luizfiliperm.pms.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pms/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    @PostMapping("/{propertyId}")
    public ResponseEntity<RoomDtoResponse> saveRoom(@RequestBody @Valid RoomDtoReceive roomDtoReceive, @PathVariable Long propertyId){
           return ResponseEntity.status(HttpStatus.CREATED).body(roomService.save(roomDtoReceive, propertyId));
    }

    @GetMapping
    public ResponseEntity<RoomDtoResponse> findRoomByIdAndPropertyId(@RequestParam Long propertyId, @RequestParam Long roomId){
        return ResponseEntity.ok(roomService.findByIdAndPropertyId(roomId, propertyId));
    }

}

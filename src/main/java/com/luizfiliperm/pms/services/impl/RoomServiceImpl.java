package com.luizfiliperm.pms.services.impl;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.room.RoomDtoReceive;
import com.luizfiliperm.pms.dtos.room.RoomDtoResponse;
import com.luizfiliperm.pms.entities.property.Property;
import com.luizfiliperm.pms.entities.room.Room;
import com.luizfiliperm.pms.exceptions.PmsException;
import com.luizfiliperm.pms.repositories.PropertyRepository;
import com.luizfiliperm.pms.repositories.RoomRepository;
import com.luizfiliperm.pms.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Override
    public RoomDtoResponse save(RoomDtoReceive roomDtoReceive, Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PmsException("Property not found with id: " + propertyId, HttpStatus.NOT_FOUND));
        Room room = roomDtoReceive.convertToRoom();
        room.setProperty(property);
        return new RoomDtoResponse(roomRepository.save(room));
    }

    @Override
    public RoomDtoResponse findByIdAndPropertyId(Long id, Long propertyId) {
        return new RoomDtoResponse(roomRepository.findByIdAndPropertyId(id, propertyId).orElseThrow(() -> new PmsException("Room not found with id: " + id, HttpStatus.NOT_FOUND)));
    }

    @Override
    public PageResponse<RoomDtoResponse> findAll(int page, int size, String sortBy, String sortDir, Long propertyId) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public RoomDtoReceive updateById(Long id, RoomDtoReceive roomDtoReceive) {
        return null;
    }
}

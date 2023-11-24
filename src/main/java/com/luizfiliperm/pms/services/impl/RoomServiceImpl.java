package com.luizfiliperm.pms.services.impl;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.room.RoomDtoReceive;
import com.luizfiliperm.pms.dtos.room.RoomDtoResponse;
import com.luizfiliperm.pms.entities.property.Property;
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
        return new RoomDtoResponse(roomRepository.save(roomDtoReceive.convertToRoom()), propertyId);
    }

    @Override
    public RoomDtoResponse findById(Long id) {
        return null;
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

package com.luizfiliperm.pms.services.impl;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.property.PropertyDtoResponse;
import com.luizfiliperm.pms.dtos.room.RoomDtoReceive;
import com.luizfiliperm.pms.dtos.room.RoomDtoResponse;
import com.luizfiliperm.pms.entities.property.Property;
import com.luizfiliperm.pms.entities.room.Room;
import com.luizfiliperm.pms.exceptions.PmsException;
import com.luizfiliperm.pms.repositories.PropertyRepository;
import com.luizfiliperm.pms.repositories.RoomRepository;
import com.luizfiliperm.pms.services.RoomService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Room> roomsPage = roomRepository.findAllByPropertyId(propertyId, pageable);
        List<Room> roomList = roomsPage.getContent();

        List<RoomDtoResponse> content = roomList.stream().map(RoomDtoResponse::new).toList();

        return new PageResponse<RoomDtoResponse>(content, roomsPage.getNumber(), roomsPage.getSize(), roomsPage.getTotalElements(), roomsPage.getTotalPages(), roomsPage.isLast());
    }

    @Override
    @Transactional
    public void delete(Long id, Long propertyId) {
        roomRepository.deleteByIdAndPropertyId(id, propertyId);
    }

    @Override
    public RoomDtoReceive updateById(Long id,Long propertyID, RoomDtoReceive roomDtoReceive) {
        Room existingRoom = roomRepository.findByIdAndPropertyId(id, propertyID).orElseThrow(() -> new PmsException(("Room not found with id: " + id), HttpStatus.NOT_FOUND));

        existingRoom.setStatus(roomDtoReceive.getStatus());
        existingRoom.setType(roomDtoReceive.getType());
        existingRoom.setNumber(roomDtoReceive.getNumber());
        existingRoom.setFloor(roomDtoReceive.getFloor());
        existingRoom.setDescription(roomDtoReceive.getDescription());


        return new RoomDtoReceive(roomRepository.save(existingRoom));
    }
}

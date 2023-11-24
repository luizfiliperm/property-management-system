package com.luizfiliperm.pms.services;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.room.RoomDtoReceive;
import com.luizfiliperm.pms.dtos.room.RoomDtoResponse;

public interface RoomService {

    RoomDtoResponse save(RoomDtoReceive roomDtoReceive, Long propertyId);

    RoomDtoResponse findById(Long id);

    PageResponse<RoomDtoResponse> findAll(int page, int size, String sortBy, String sortDir, Long propertyId);

    void delete(Long id);

    RoomDtoReceive updateById(Long id, RoomDtoReceive roomDtoReceive);

}

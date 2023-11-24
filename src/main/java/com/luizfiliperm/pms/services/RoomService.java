package com.luizfiliperm.pms.services;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.room.RoomDtoReceive;
import com.luizfiliperm.pms.dtos.room.RoomDtoResponse;

public interface RoomService {

    RoomDtoResponse save(RoomDtoReceive roomDtoReceive, Long propertyId);

    RoomDtoResponse findByIdAndPropertyId(Long id, Long propertyId);

    PageResponse<RoomDtoResponse> findAll(int page, int size, String sortBy, String sortDir, Long propertyId);

    void delete(Long id, Long propertyId);

    RoomDtoReceive updateById(Long id,Long propertyId, RoomDtoReceive roomDtoReceive);

}

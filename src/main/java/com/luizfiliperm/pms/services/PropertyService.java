package com.luizfiliperm.pms.services;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.property.PropertyDtoReceive;
import com.luizfiliperm.pms.dtos.property.PropertyDtoResponse;

public interface PropertyService {

    PropertyDtoResponse save(PropertyDtoReceive propertyDtoReceive);

    PropertyDtoResponse findById(Long id);

    PageResponse<PropertyDtoResponse> findAll(int page, int size, String sortBy, String sortDir);

    void delete(Long id);

    PropertyDtoResponse updateById(Long id, PropertyDtoReceive propertyDtoResponse);
}

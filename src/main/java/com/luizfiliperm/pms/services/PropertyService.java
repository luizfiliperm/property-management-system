package com.luizfiliperm.pms.services;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.property.PropertyDtoReceive;
import com.luizfiliperm.pms.dtos.property.PropertyDtoResponse;
import com.luizfiliperm.pms.entities.property.Property;
import com.luizfiliperm.pms.entities.user.User;

public interface PropertyService {

    PropertyDtoResponse save(PropertyDtoReceive propertyDtoReceive, User user);

    PropertyDtoResponse findById(Long id);

    PageResponse<PropertyDtoResponse> findAll(int page, int size, String sortBy, String sortDir);

    void delete(Long id);

    PropertyDtoResponse update(Property property, PropertyDtoReceive propertyDtoResponse);
}

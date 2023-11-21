package com.luizfiliperm.pms.services;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.PropertyDto;

public interface PropertyService {

    PropertyDto save(PropertyDto propertyDto);

    PropertyDto findById(Long id);

    PageResponse<PropertyDto> findAll(int page, int size, String sortBy, String sortDir);

    void delete(Long id);
}

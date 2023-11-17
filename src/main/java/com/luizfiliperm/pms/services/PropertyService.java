package com.luizfiliperm.pms.services;

import com.luizfiliperm.pms.dtos.PropertyDto;

public interface PropertyService {

    PropertyDto register(PropertyDto propertyDto);

    PropertyDto findById(Long id);
}

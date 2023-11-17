package com.luizfiliperm.pms.services.impl;

import com.luizfiliperm.pms.dtos.PropertyDto;
import com.luizfiliperm.pms.entities.Address;
import com.luizfiliperm.pms.entities.Property;
import com.luizfiliperm.pms.repositories.AddressRepository;
import com.luizfiliperm.pms.repositories.PropertyRepository;
import com.luizfiliperm.pms.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public PropertyDto register(PropertyDto propertyDto) {
        Address address = propertyDto.getAddress().convertToAddress();
        addressRepository.save(address);

        Property property = propertyDto.convertToProperty();
        property.setAddress(address);
        return new PropertyDto(propertyRepository.save(property));

    }

    @Override
    public PropertyDto findById(Long id) {
        return new PropertyDto(propertyRepository.findById(id).orElseThrow());
    }
}

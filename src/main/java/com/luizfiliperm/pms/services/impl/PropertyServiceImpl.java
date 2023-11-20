package com.luizfiliperm.pms.services.impl;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.PropertyDto;
import com.luizfiliperm.pms.entities.Address;
import com.luizfiliperm.pms.entities.Property;
import com.luizfiliperm.pms.repositories.AddressRepository;
import com.luizfiliperm.pms.repositories.PropertyRepository;
import com.luizfiliperm.pms.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public PropertyDto save(PropertyDto propertyDto) {
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

    @Override
    public PageResponse<PropertyDto> findAll(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Property> properties = propertyRepository.findAll(pageable);

        List<Property> propertyList = properties.getContent();

        List<PropertyDto> content = propertyList.stream().map(PropertyDto::new).toList();
        return new PageResponse<PropertyDto>(content, properties.getNumber(), properties.getSize(), properties.getTotalElements(), properties.getTotalPages(), properties.isLast());
    }


    @Override
    public void remove(Long id) {

    }
}

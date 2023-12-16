package com.luizfiliperm.pms.services.impl;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.property.PropertyDtoReceive;
import com.luizfiliperm.pms.dtos.property.PropertyDtoResponse;
import com.luizfiliperm.pms.entities.property.Property;
import com.luizfiliperm.pms.entities.user.User;
import com.luizfiliperm.pms.exceptions.PmsException;
import com.luizfiliperm.pms.repositories.AddressRepository;
import com.luizfiliperm.pms.repositories.PropertyRepository;
import com.luizfiliperm.pms.repositories.UserRepository;
import com.luizfiliperm.pms.services.PropertyService;
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
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public PropertyDtoResponse save(PropertyDtoReceive propertyDtoReceive, User user) {
        if(user.getProperty() != null){
            throw new PmsException("User already has a property", HttpStatus.BAD_REQUEST);
        }
        Property property = propertyRepository.save(propertyDtoReceive.convertToProperty());

        user.setProperty(property);
        userRepository.save(user);
        return new PropertyDtoResponse(property);
    }

    @Override
    public PropertyDtoResponse save(PropertyDtoReceive propertyDtoReceive, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new PmsException("User not found with id: " + userId, HttpStatus.NOT_FOUND));
        return this.save(propertyDtoReceive, user);
    }

    @Override
    public PropertyDtoResponse findById(Long id) {
        return new PropertyDtoResponse(propertyRepository.findById(id).orElseThrow(() -> new PmsException("Property not found with id: " + id, HttpStatus.NOT_FOUND)));
    }

    @Override
    public PageResponse<PropertyDtoResponse> findAll(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Property> properties = propertyRepository.findAll(pageable);

        List<Property> propertyList = properties.getContent();

        List<PropertyDtoResponse> content = propertyList.stream().map(PropertyDtoResponse::new).toList();
        return new PageResponse<PropertyDtoResponse>(content, properties.getNumber(), properties.getSize(), properties.getTotalElements(), properties.getTotalPages(), properties.isLast());
    }


    @Override
    @Transactional
    public void delete(Long id) {
        propertyRepository.deleteById(id);

    }

    @Override
    public PropertyDtoResponse update(Property property, PropertyDtoReceive propertyDtoReceive) {

        property.setName(propertyDtoReceive.getName());
        property.setContact(propertyDtoReceive.getContact());
        property.setDescription(propertyDtoReceive.getDescription());
        property.getAddress().setCep(propertyDtoReceive.getAddress().getCep());
        property.getAddress().setNumber(propertyDtoReceive.getAddress().getNumber());
        property.getAddress().setStreet(propertyDtoReceive.getAddress().getStreet());
        property.getAddress().setCity(propertyDtoReceive.getAddress().getCity());
        property.getAddress().setState(propertyDtoReceive.getAddress().getState());

        return new PropertyDtoResponse(propertyRepository.save(property));
    }
}

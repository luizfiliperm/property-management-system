package com.luizfiliperm.pms.controllers;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.property.PropertyDtoReceive;
import com.luizfiliperm.pms.dtos.property.PropertyDtoResponse;
import com.luizfiliperm.pms.exceptions.PmsException;
import com.luizfiliperm.pms.services.PropertyService;
import com.luizfiliperm.pms.util.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pms/properties")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyDtoResponse> saveProperty(@Valid @RequestBody PropertyDtoReceive propertyDtoReceive) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.save(propertyDtoReceive));
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyDtoResponse> findPropertyById(@PathVariable Long propertyId) {
        return ResponseEntity.ok(propertyService.findById(propertyId));
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Void> deletePropertyById(@PathVariable Long propertyId){
        propertyService.delete(propertyId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<PropertyDtoResponse> updatePropertyById(@PathVariable Long propertyId, @Valid @RequestBody PropertyDtoReceive propertyDtoReceive){
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.updateById(propertyId, propertyDtoReceive));
    }
}

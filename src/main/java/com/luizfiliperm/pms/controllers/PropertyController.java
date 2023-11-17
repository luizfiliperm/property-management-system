package com.luizfiliperm.pms.controllers;

import com.luizfiliperm.pms.dtos.PropertyDto;
import com.luizfiliperm.pms.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pms/properties")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyDto> saveProperty(@RequestBody PropertyDto propertyDto){
        return ResponseEntity.ok(propertyService.register(propertyDto));
    }
}

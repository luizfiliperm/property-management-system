package com.luizfiliperm.pms.controllers;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.property.PropertyDtoReceive;
import com.luizfiliperm.pms.dtos.property.PropertyDtoResponse;
import com.luizfiliperm.pms.entities.user.User;
import com.luizfiliperm.pms.exceptions.PmsException;
import com.luizfiliperm.pms.services.PropertyService;
import com.luizfiliperm.pms.util.AppConstants;
import com.luizfiliperm.pms.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pms/properties")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyDtoResponse> saveProperty(@Valid @RequestBody PropertyDtoReceive propertyDtoReceive, Authentication authentication) {

        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.save(propertyDtoReceive, AuthUtil.retrieveUserFromAuthentication(authentication)));
    }

    @GetMapping
    public ResponseEntity<PropertyDtoResponse> getProperty(Authentication authentication) {
        return ResponseEntity.ok(propertyService.findById(AuthUtil.retrievePropertyFromAuthentication(authentication).getId()));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProperty(Authentication authentication){
        propertyService.delete(AuthUtil.retrievePropertyFromAuthentication(authentication).getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping
    public ResponseEntity<PropertyDtoResponse> updateProperty(@Valid @RequestBody PropertyDtoReceive propertyDtoReceive, Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.update(AuthUtil.retrievePropertyFromAuthentication(authentication), propertyDtoReceive));
    }
}

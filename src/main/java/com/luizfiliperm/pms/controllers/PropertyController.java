package com.luizfiliperm.pms.controllers;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.property.PropertyDtoReceive;
import com.luizfiliperm.pms.dtos.property.PropertyDtoResponse;
import com.luizfiliperm.pms.exceptions.PmsException;
import com.luizfiliperm.pms.services.PropertyService;
import com.luizfiliperm.pms.util.AppConstants;
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
    public ResponseEntity<PropertyDtoResponse> saveProperty(@RequestBody PropertyDtoReceive propertyDtoReceive) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.save(propertyDtoReceive));
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyDtoResponse> findPropertyById(@PathVariable Long propertyId) {
        return ResponseEntity.ok(propertyService.findById(propertyId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<PropertyDtoResponse>> findAll(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                                     @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                                     @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                                     @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(propertyService.findAll(pageNo, pageSize, sortBy, sortDir));
        }catch (PropertyReferenceException e){
            throw new PmsException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Void> deletePropertyById(@PathVariable Long propertyId){
        propertyService.delete(propertyId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<PropertyDtoResponse> updatePropertyById(@PathVariable Long propertyId, @RequestBody PropertyDtoReceive propertyDtoReceive){
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.updateById(propertyId, propertyDtoReceive));
    }
}

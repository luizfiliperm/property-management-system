package com.luizfiliperm.pms.controllers.admin;

import com.luizfiliperm.pms.dtos.PageResponse;
import com.luizfiliperm.pms.dtos.property.PropertyDtoReceive;
import com.luizfiliperm.pms.dtos.property.PropertyDtoResponse;
import com.luizfiliperm.pms.services.PropertyService;
import com.luizfiliperm.pms.util.AppConstants;
import com.luizfiliperm.pms.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pms/admin")
public class AdminPropertyController {

    @Autowired
    PropertyService propertyService;

    @GetMapping("/properties")
    public ResponseEntity<PageResponse<PropertyDtoResponse>> findAll(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                                     @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                                     @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                                     @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        return ResponseEntity.status(HttpStatus.OK).body(propertyService.findAll(pageNo, pageSize, sortBy, sortDir));
    }

    @PostMapping("/properties/{userId}")
    public ResponseEntity<PropertyDtoResponse> saveProperty(@Valid @RequestBody PropertyDtoReceive propertyDtoReceive, @RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.save(propertyDtoReceive, userId));
    }
}

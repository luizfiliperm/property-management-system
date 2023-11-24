package com.luizfiliperm.pms.repositories;

import com.luizfiliperm.pms.entities.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}

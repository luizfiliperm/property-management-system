package com.luizfiliperm.pms.repositories;

import com.luizfiliperm.pms.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

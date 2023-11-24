package com.luizfiliperm.pms.repositories;

import com.luizfiliperm.pms.entities.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByIdAndPropertyId(Long id, Long propertyId);
}

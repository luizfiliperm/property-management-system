package com.luizfiliperm.pms.repositories;

import com.luizfiliperm.pms.entities.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}

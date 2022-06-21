package com.eleks.game.repository;

import com.eleks.game.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository
{
    Room saveRoom(Room room);

    Optional<Room> findById(String roomId);

    List<Room> findAllRooms();
}

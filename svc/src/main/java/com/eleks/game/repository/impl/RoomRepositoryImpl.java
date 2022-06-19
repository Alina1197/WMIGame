package com.eleks.game.repository.impl;

import com.eleks.game.entity.Room;
import com.eleks.game.enums.RoomState;
import com.eleks.game.repository.RoomRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RoomRepositoryImpl implements RoomRepository
{
    private final List<Room> roomList = new ArrayList<>();

    @Override
    public Room saveRoom(Room room)
    {
        roomList.add(room);
        return room;
    }

    @Override
    public Optional<Room> findById(String roomId)
    {
        return roomList.stream().filter(room -> Objects.equals(room.getId(), roomId)).findFirst();
    }

    @Override
    public List<Room> findAllRooms(String playerId)
    {
        return roomList.stream()
            .filter(room -> room.getRoomState().equals(RoomState.WAITING_FOR_PLAYERS))
            .collect(Collectors.toList());
    }
}

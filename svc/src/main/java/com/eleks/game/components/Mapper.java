package com.eleks.game.components;

import com.eleks.game.model.request.RoomRequest;
import com.eleks.game.entity.Room;

import java.util.Objects;

public class Mapper
{
    public static Room mapToEntity(RoomRequest requestDto)
    {
        Room room = new Room();
        if (Objects.isNull(requestDto))
        {
            throw new IllegalArgumentException("Room is null!");
        }
        room.setId(requestDto.getId());
        return room;
    }

    private Mapper()
    {
    }
}

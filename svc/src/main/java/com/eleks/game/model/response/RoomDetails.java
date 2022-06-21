package com.eleks.game.model.response;

import com.eleks.game.core.impl.GamePlayer;
import com.eleks.game.entity.Room;
import com.eleks.game.enums.RoomState;

import java.util.List;
import java.util.Objects;

public class RoomDetails
{
    private String id;
    private RoomState roomState;
    private List<GamePlayer> players;
    private List<GamePlayer> winners;

    public RoomDetails(String id, RoomState roomState, List<GamePlayer> players, List<GamePlayer> winners)
    {
        this.id = id;
        this.roomState = roomState;
        this.players = players;
        this.winners = winners;
    }

    public RoomDetails()
    {
    }

    public static RoomDetails of(Room room)
    {
        return new RoomDetails(room.getId(), room.getRoomState(), room.getRandomPlayers(), room.getWinnerList());
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public RoomState getRoomState()
    {
        return roomState;
    }

    public void setRoomState(RoomState roomState)
    {
        this.roomState = roomState;
    }

    public List<GamePlayer> getPlayers()
    {
        return players;
    }

    public void setPlayers(List<GamePlayer> players)
    {
        this.players = players;
    }

    public List<GamePlayer> getWinners()
    {
        return winners;
    }

    public void setWinners(List<GamePlayer> winners)
    {
        this.winners = winners;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        RoomDetails that = (RoomDetails) o;
        return Objects.equals(id, that.id) && roomState == that.roomState && Objects.equals(players, that.players) &&
            Objects.equals(winners, that.winners);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, roomState, players, winners);
    }

    @Override
    public String toString()
    {
        return "RoomDetails{" +
            "id='" + id + '\'' +
            ", roomState=" + roomState +
            ", players=" + players +
            ", winners=" + winners +
            '}';
    }
}
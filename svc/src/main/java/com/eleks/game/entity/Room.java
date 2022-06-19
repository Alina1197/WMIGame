package com.eleks.game.entity;

import com.eleks.game.core.Game;
import com.eleks.game.core.impl.RandomPlayer;
import com.eleks.game.enums.RoomState;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room
{
    private String id;
    private List<RandomPlayer> randomPlayers = new ArrayList<>(4);
    private RoomState roomState = RoomState.WAITING_FOR_PLAYERS;
    private Game game;

    public Room()
    {
    }

    public Room(String id, List<RandomPlayer> randomPlayers, RoomState roomState, Game game)
    {
        this.id = id;
        this.randomPlayers = randomPlayers;
        this.roomState = roomState;
        this.game = game;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public List<RandomPlayer> getRandomPlayers()
    {
        return randomPlayers;
    }

    public void setRandomPlayers(List<RandomPlayer> randomPlayers)
    {
        this.randomPlayers = randomPlayers;
    }

    public RoomState getRoomState()
    {
        return roomState;
    }

    public void setRoomState(RoomState roomState)
    {
        this.roomState = roomState;
    }

    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
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
        Room room = (Room) o;
        return Objects.equals(id, room.id) && Objects.equals(randomPlayers, room.randomPlayers) && roomState == room.roomState &&
            Objects.equals(game, room.game);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, randomPlayers, roomState, game);
    }

    @Override
    public String toString()
    {
        return "Room{" +
            "id='" + id + '\'' +
            ", randomPlayers=" + randomPlayers +
            ", roomState=" + roomState +
            ", game=" + game +
            '}';
    }
}

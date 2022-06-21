package com.eleks.game.entity;

import com.eleks.game.core.Game;
import com.eleks.game.core.impl.GamePlayer;
import com.eleks.game.enums.RoomState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Room
{
    private String id;
    private List<GamePlayer> gamePlayers = new ArrayList<>(4);
    private RoomState roomState = RoomState.WAITING_FOR_PLAYERS;
    private Game game;
    private List<GamePlayer> winnerList = new LinkedList<>();

    public Room()
    {
    }

    public Room(String id, List<GamePlayer> gamePlayers, RoomState roomState, Game game)
    {
        this.id = id;
        this.gamePlayers = gamePlayers;
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

    public List<GamePlayer> getRandomPlayers()
    {
        return gamePlayers;
    }

    public void setRandomPlayers(List<GamePlayer> gamePlayers)
    {
        this.gamePlayers = gamePlayers;
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

    public List<GamePlayer> getWinnerList()
    {
        return winnerList;
    }

    public void setWinnerList(List<GamePlayer> winnerList)
    {
        this.winnerList = winnerList;
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
        return Objects.equals(id, room.id) && Objects.equals(gamePlayers, room.gamePlayers) && roomState == room.roomState &&
            Objects.equals(game, room.game) && Objects.equals(winnerList, room.winnerList);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, gamePlayers, roomState, game, winnerList);
    }

    @Override
    public String toString()
    {
        return "Room{" +
            "id='" + id + '\'' +
            ", randomPlayers=" + gamePlayers +
            ", roomState=" + roomState +
            ", game=" + game +
            ", winnerList=" + winnerList +
            '}';
    }
}

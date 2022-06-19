package com.eleks.game.model.response;

import com.eleks.game.core.impl.RandomGame;

import java.util.Objects;

public class GameDetails
{
    private String currentTurn;

    public GameDetails(String currentTurn)
    {
        this.currentTurn = currentTurn;
    }

    public GameDetails()
    {
    }

    public static GameDetails of(RandomGame game)
    {
        return new GameDetails(game.getTurn());
    }

    public String getCurrentTurn()
    {
        return currentTurn;
    }

    public void setCurrentTurn(String currentTurn)
    {
        this.currentTurn = currentTurn;
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
        GameDetails that = (GameDetails) o;
        return Objects.equals(currentTurn, that.currentTurn);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(currentTurn);
    }

    @Override
    public String toString()
    {
        return "GameDetails{" +
            "currentTurn='" + currentTurn + '\'' +
            '}';
    }
}

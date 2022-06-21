package com.eleks.game.model.response;

import com.eleks.game.core.Turn;
import com.eleks.game.core.impl.GameImpl;

import java.util.Objects;

public class GameDetails
{
    private Turn currentTurn;

    public GameDetails()
    {
    }

    public GameDetails(Turn turn)
    {
        this.currentTurn = turn;
    }

    public static GameDetails of(GameImpl game)
    {
        return new GameDetails(game.getTurn());
    }

    public Turn getCurrentTurn()
    {
        return currentTurn;
    }

    public void setCurrentTurn(Turn currentTurn)
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

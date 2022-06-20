package com.eleks.game.model.response;

import java.util.Objects;

public class TurnDetails
{
    private String player;
    private String character;
    private boolean guessing;

    public TurnDetails(String player, String character, boolean guessing)
    {
        this.player = player;
        this.character = character;
        this.guessing = guessing;
    }

    public String getPlayer()
    {
        return player;
    }

    public void setPlayer(String player)
    {
        this.player = player;
    }

    public String getCharacter()
    {
        return character;
    }

    public void setCharacter(String character)
    {
        this.character = character;
    }

    public boolean isGuessing()
    {
        return guessing;
    }

    public void setGuessing(boolean guessing)
    {
        this.guessing = guessing;
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
        TurnDetails that = (TurnDetails) o;
        return guessing == that.guessing && Objects.equals(player, that.player) && Objects.equals(character, that.character);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(player, character, guessing);
    }

    @Override
    public String toString()
    {
        return "TurnDetails{" +
            "player='" + player + '\'' +
            ", character='" + character + '\'' +
            ", guessing=" + guessing +
            '}';
    }
}

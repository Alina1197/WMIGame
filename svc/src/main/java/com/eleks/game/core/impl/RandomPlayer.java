package com.eleks.game.core.impl;

import com.eleks.game.core.Player;
import com.eleks.game.enums.PlayerState;

import java.util.Objects;

public class RandomPlayer implements Player
{
    private String id;
    private String roomId;
    private String nickname;
    private String character;
    private boolean suggestStatus = false;
    private PlayerState playerState;
    private boolean enteredAnswer;
    private boolean enteredQuestion;

    public RandomPlayer(String id, String roomId, String nickname)
    {
        this.id = id;
        this.roomId = roomId;
        this.nickname = nickname;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getRoomId()
    {
        return roomId;
    }

    public void setRoomId(String roomId)
    {
        this.roomId = roomId;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getCharacter()
    {
        return character;
    }

    public void setCharacter(String character)
    {
        this.character = character;
    }

    public boolean isSuggestStatus()
    {
        return suggestStatus;
    }

    public void setSuggestStatus(boolean suggestStatus)
    {
        this.suggestStatus = suggestStatus;
    }

    public PlayerState getPlayerState()
    {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState)
    {
        this.playerState = playerState;
    }

    public boolean isEnteredAnswer()
    {
        return enteredAnswer;
    }

    public void setEnteredAnswer(boolean enteredAnswer)
    {
        this.enteredAnswer = enteredAnswer;
    }

    public boolean isEnteredQuestion()
    {
        return enteredQuestion;
    }

    public void setEnteredQuestion(boolean enteredQuestion)
    {
        this.enteredQuestion = enteredQuestion;
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
        RandomPlayer that = (RandomPlayer) o;
        return suggestStatus == that.suggestStatus && enteredAnswer == that.enteredAnswer && enteredQuestion == that.enteredQuestion && Objects.equals(id, that.id) &&
            Objects.equals(roomId, that.roomId) && Objects.equals(nickname, that.nickname) && Objects.equals(character, that.character) &&
            playerState == that.playerState;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, roomId, nickname, character, suggestStatus, playerState, enteredAnswer, enteredQuestion);
    }

    @Override
    public String toString()
    {
        return "RandomPlayer{" +
            "id='" + id + '\'' +
            ", roomId='" + roomId + '\'' +
            ", nickname='" + nickname + '\'' +
            ", character='" + character + '\'' +
            ", suggestStatus=" + suggestStatus +
            ", playerState=" + playerState +
            ", enteredAnswer=" + enteredAnswer +
            ", enteredQuestion=" + enteredQuestion +
            '}';
    }
}

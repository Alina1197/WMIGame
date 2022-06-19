package com.eleks.game.core.impl;

import com.eleks.game.core.Player;
import com.eleks.game.enums.PlayerState;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class RandomPlayer implements Player
{
    private String id;
    private String roomId;
    private String nickname;
    private String character;
    private boolean suggestStatus = false;
    private PlayerState playerState;

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

    @Override
    public String getName()
    {
        return getNickname();
    }

    @Override
    public Future<String> getQuestion(String question)
    {
        return CompletableFuture.completedFuture(question);
    }

    @Override
    public Future<String> askQuestion(String question, String character)
    {
        return CompletableFuture.completedFuture(question);
    }

    @Override
    public Future<String> answerQuestion(String question, String character, String answer)
    {
        return CompletableFuture.completedFuture(answer);
    }

}

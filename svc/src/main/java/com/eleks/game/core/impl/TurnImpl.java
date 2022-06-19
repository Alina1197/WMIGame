package com.eleks.game.core.impl;

import com.eleks.game.core.Player;
import com.eleks.game.core.Turn;
import com.eleks.game.enums.PlayerState;

import java.util.List;
import java.util.stream.Collectors;

public class TurnImpl implements Turn
{

    private final List<RandomPlayer> players;
    private int currentPlayerIndex = 0;

    public TurnImpl(List<RandomPlayer> players)
    {
        this.players = players;
    }

    @Override
    public Player getGuesser()
    {
        var player = this.players.get(currentPlayerIndex);
        player.setPlayerState(PlayerState.ASK_QUESTION);
        return player;
    }

    @Override
    public List<RandomPlayer> getOtherPlayers()
    {
        return this.players.stream()
            .filter(player -> !player.getName().equals(this.getGuesser().getName()))
            .collect(Collectors.toList());
    }

    @Override
    public void changeTurn()
    {
        this.currentPlayerIndex = this.currentPlayerIndex + 1 >= this.players.size() ? 0 : this.currentPlayerIndex + 1;
        var player = players.get(this.currentPlayerIndex);
        player.setPlayerState(PlayerState.ASK_QUESTION);
        players.stream()
            .filter(randomPlayer -> !randomPlayer.equals(player))
            .forEach(randomPlayer -> randomPlayer.setPlayerState(PlayerState.ANSWER_QUESTION));
    }
}

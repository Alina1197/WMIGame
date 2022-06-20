package com.eleks.game.core.impl;

import com.eleks.game.core.Turn;
import com.eleks.game.enums.PlayerState;
import com.eleks.game.enums.QuestionAnswer;

import java.util.ArrayList;
import java.util.List;

public class TurnImpl implements Turn
{
    private final List<RandomPlayer> players;
    private final List<QuestionAnswer> playersAnswers = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public TurnImpl(List<RandomPlayer> players)
    {
        this.players = players;
    }

    @Override
    public List<QuestionAnswer> getPlayersAnswers()
    {
        if (playersAnswers.size() == players.size() - 1)
        {
            playersAnswers.clear();
        }
        return playersAnswers;
    }

    @Override
    public RandomPlayer getGuesser()
    {
        return this.players.get(currentPlayerIndex);
    }

    @Override
    public void changeTurn()
    {
        this.currentPlayerIndex = this.currentPlayerIndex + 1 >= this.players.size() ? 0 : this.currentPlayerIndex + 1;
        var player = players.get(this.currentPlayerIndex);
        player.setPlayerState(PlayerState.ASK_QUESTION);
        players.stream()
            .filter(randomPlayer -> !randomPlayer.getId().equals(player.getId()))
            .forEach(randomPlayer -> randomPlayer.setPlayerState(PlayerState.ANSWER_QUESTION));
    }
}

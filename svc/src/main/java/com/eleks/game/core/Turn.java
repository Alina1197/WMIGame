package com.eleks.game.core;

import com.eleks.game.core.impl.RandomPlayer;
import com.eleks.game.enums.QuestionAnswer;

import java.util.List;

public interface Turn
{
    RandomPlayer getGuesser();

    void changeTurn(List<RandomPlayer> players);

    List<QuestionAnswer> getPlayersAnswers();
}

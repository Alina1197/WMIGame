package com.eleks.game.core;

import com.eleks.game.core.impl.GamePlayer;
import com.eleks.game.enums.QuestionAnswer;

import java.util.List;

public interface Turn
{
    GamePlayer getGuesser();

    void changeTurn(List<GamePlayer> players);

    List<QuestionAnswer> getPlayersAnswers();
}

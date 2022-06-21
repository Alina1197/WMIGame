package com.eleks.game.core;

import com.eleks.game.core.impl.GamePlayer;
import com.eleks.game.enums.QuestionAnswer;
import com.eleks.game.model.request.QuestionRequest;

import java.util.List;

public interface Game
{
    void startGame();

    List<GamePlayer> getGamePLayers();

    Turn getTurn();

    boolean isFinished();

    void askQuestion(String roomId, String player, QuestionRequest askQuestion);

    void answerQuestion(String roomId, String player, QuestionAnswer askQuestion);

    void askGuessingQuestion(String roomId, String player, QuestionRequest askQuestion, boolean guessStatus);

    void answerGuessingQuestion(String roomId, String player, QuestionAnswer askQuestion, boolean guessStatus);
}

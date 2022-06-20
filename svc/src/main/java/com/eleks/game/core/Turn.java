package com.eleks.game.core;

import com.eleks.game.core.impl.RandomPlayer;
import com.eleks.game.enums.QuestionAnswer;
import com.eleks.game.model.response.QuestionResponse;

import java.util.List;

public interface Turn
{
    RandomPlayer getGuesser();

    List<RandomPlayer> getOtherPlayers();

    void changeTurn();

    String getPlayerCharacter();

    String getPlayerQuestion();

    List<QuestionAnswer> getPlayersAnswers();


    String getQuestion(String question);

    QuestionResponse askQuestion(String question, String character);

    QuestionResponse askGuessingQuestion(String question, String character, boolean guess);

    QuestionAnswer answerQuestion(String question, String character, QuestionAnswer answer);
}

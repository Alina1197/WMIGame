package com.eleks.game.core.impl;

import com.eleks.game.core.Turn;
import com.eleks.game.enums.PlayerState;
import com.eleks.game.enums.QuestionAnswer;
import com.eleks.game.model.response.QuestionResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TurnImpl implements Turn
{
    private final List<RandomPlayer> players;
    private final List<QuestionAnswer> playersAnswers = new ArrayList<>();
    private String playerQuestion;
    private String playerCharacter;
    private int currentPlayerIndex = 0;

    public TurnImpl(List<RandomPlayer> players)
    {
        this.players = players;
    }

    @Override
    public List<QuestionAnswer> getPlayersAnswers()
    {
        if (playersAnswers.size() == 3)
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
    public List<RandomPlayer> getOtherPlayers()
    {
        return this.players.stream()
            .filter(player -> !player.getNickname().equals(this.getGuesser().getNickname()))
            .collect(Collectors.toList());
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

    @Override
    public String getPlayerCharacter()
    {
        return playerCharacter;
    }

    public void setPlayerCharacter(String playerCharacter)
    {
        this.playerCharacter = playerCharacter;
    }

    @Override
    public String getPlayerQuestion()
    {
        return getPlayerCharacter();
    }

    public void setPlayerQuestion(String playerQuestion)
    {
        this.playerQuestion = playerQuestion;
    }

    @Override
    public String getQuestion(String question)
    {
        return question;
    }

    @Override
    public QuestionResponse askQuestion(String question, String character)
    {
        return new QuestionResponse(question, character);
    }

    @Override
    public QuestionResponse askGuessingQuestion(String question, String character, boolean guess)
    {
        return new QuestionResponse(question, character, guess);
    }

    @Override
    public QuestionAnswer answerQuestion(String question, String character, QuestionAnswer answer)
    {
        return answer;
    }
}

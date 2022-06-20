package com.eleks.game.model.response;

import java.util.Objects;

public class QuestionResponse
{
    private String question;
    private String playerCharacter;
    private boolean guess;

    public QuestionResponse(String question, String playerCharacter, boolean guess)
    {
        this.question = question;
        this.playerCharacter = playerCharacter;
        this.guess = guess;
    }

    public QuestionResponse(String question, String playerCharacter)
    {
        this.question = question;
        this.playerCharacter = playerCharacter;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getPlayerCharacter()
    {
        return playerCharacter;
    }

    public void setPlayerCharacter(String playerCharacter)
    {
        this.playerCharacter = playerCharacter;
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
        QuestionResponse that = (QuestionResponse) o;
        return Objects.equals(question, that.question) && Objects.equals(playerCharacter, that.playerCharacter);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(question, playerCharacter);
    }
}

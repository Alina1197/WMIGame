package com.eleks.game.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class QuestionRequest
{

    @NotBlank
    @Size(min = 2, max = 128, message = "question length should be between {min} and {max}")
    private String question;

    public QuestionRequest(String question)
    {
        this.question = question;
    }

    public QuestionRequest()
    {
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
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
        QuestionRequest that = (QuestionRequest) o;
        return Objects.equals(question, that.question);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(question);
    }

    @Override
    public String toString()
    {
        return "AskRequest{" +
            "question='" + question + '\'' +
            '}';
    }
}

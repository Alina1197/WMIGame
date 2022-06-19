package com.eleks.game.exception.handler;

import java.util.List;
import java.util.Objects;

public class ErrorResponse
{
    private String message;
    private List<String> details;

    public ErrorResponse(String message, List<String> details)
    {
        this.message = message;
        this.details = details;
    }

    public ErrorResponse()
    {
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public List<String> getDetails()
    {
        return details;
    }

    public void setDetails(List<String> details)
    {
        this.details = details;
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
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(message, that.message) && Objects.equals(details, that.details);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(message, details);
    }

    @Override
    public String toString()
    {
        return "ErrorResponse{" +
            "message='" + message + '\'' +
            ", details=" + details +
            '}';
    }
}

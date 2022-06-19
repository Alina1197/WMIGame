package com.eleks.game.exception;

public class RoomNotFoundException extends RuntimeException
{
    public RoomNotFoundException(String message)
    {
        super(message);
    }
}

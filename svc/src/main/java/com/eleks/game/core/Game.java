package com.eleks.game.core;

public interface Game
{
    void startGame();

    boolean makeTurn();

    String getTurn();

    void changeTurn();

    boolean isFinished();
}

package com.eleks.game.core;

import com.eleks.game.core.impl.RandomPlayer;

import java.util.List;

public interface Turn
{
    Player getGuesser();

    List<RandomPlayer> getOtherPlayers();

    void changeTurn();
}

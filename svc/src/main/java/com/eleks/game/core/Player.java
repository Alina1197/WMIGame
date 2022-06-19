package com.eleks.game.core;

import java.util.concurrent.Future;

public interface Player
{
    String getName();

    Future<String> getQuestion(String question);

    Future<String> askQuestion(String question, String character);

    Future<String> answerQuestion(String question, String character, String answer);

}

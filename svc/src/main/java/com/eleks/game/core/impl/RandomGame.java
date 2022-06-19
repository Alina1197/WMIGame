package com.eleks.game.core.impl;

import com.eleks.game.core.Game;
import com.eleks.game.core.Player;
import com.eleks.game.core.Turn;
import com.eleks.game.enums.GameState;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RandomGame implements Game
{
    private final List<RandomPlayer> randomPlayers;
    private final Map<String, String> playersCharacter = new ConcurrentHashMap<>();
    private Turn currentTurn;

    public RandomGame(List<RandomPlayer> randomPlayers)
    {
        this.randomPlayers = randomPlayers;
    }

    @Override
    public void startGame()
    {
//        assignCharacters();
//        GameState gameState = ;
//        while (gameState.equals)
//        {
//            boolean turnResult = this.makeTurn();
//
//            while (turnResult)
//            {
//                turnResult = this.makeTurn();
//            }
//            this.changeTurn();
//            gameStatus = !this.isFinished();
//        }
    }

    @Override
    public boolean makeTurn()
    {
        Player currentGuesser = currentTurn.getGuesser();
        Set<String> answers;

        String guessersName = currentGuesser.getName();
//
//        try
//        {
//            if (currentGuesser.isReadyForGuess().get())
//            {
//                String guess = currentGuesser.getGuess().get();
//                answers = currentTurn.getOtherPlayers().stream()
//                    .map(player -> {
//                        try
//                        {
//                            return player.answerGuess(guess, this.playersCharacter.get(guessersName)).get();
//                        } catch (InterruptedException | ExecutionException e)
//                        {
//                            throw new RuntimeException(e);
//                        }
//                    })
//                    .collect(Collectors.toSet());
//                long positiveCount = answers.stream().filter(QuestionAnswer.YES::equals).count();
//                long negativeCount = answers.stream().filter(QuestionAnswer.NO::equals).count();
//
//                boolean win = positiveCount > negativeCount;
//
//                if (win)
//                {
//                    randomPlayers.remove(currentGuesser);
//                }
//                return win;
//
//            } else
//            {
//                String question = currentGuesser.getQuestion().get();
//                answers = currentTurn.getOtherPlayers().stream()
//                    .map(player -> {
//                        try
//                        {
//                            return player.answerQuestion(question, this.playersCharacter.get(guessersName)).get();
//                        } catch (InterruptedException | ExecutionException e)
//                        {
//                            throw new RuntimeException(e);
//                        }
//                    })
//                    .collect(Collectors.toSet());
//                long positiveCount = answers.stream().filter(YES::equals).count();
//                long negativeCount = answers.stream().filter(NO::equals).count();
//                return positiveCount > negativeCount;
//            }
//        } catch (InterruptedException e)
//        {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(e);
//        } catch (ExecutionException e)
//        {
//            throw new RuntimeException(e);
//        }
        return false;
    }

    @Override
    public String getTurn()
    {
        return currentTurn.getGuesser().getName();
    }

    @Override
    public void changeTurn()
    {
        this.currentTurn.changeTurn();
    }

    @Override
    public boolean isFinished()
    {
        return randomPlayers.size() == 1;
    }

    private void assignCharacters()
    {
        var availableCharacters = randomPlayers.stream().map(RandomPlayer::getCharacter).collect(Collectors.toList());

        Random r1 = new Random();

        for (int i = availableCharacters.size() - 1; i >= 1; i--)
        {
            Collections.swap(availableCharacters, i, r1.nextInt(i + 1));
        }

        AtomicInteger a = new AtomicInteger();
        randomPlayers.forEach(randomPlayer -> randomPlayer.setCharacter(availableCharacters.get(a.getAndIncrement())));
    }

}

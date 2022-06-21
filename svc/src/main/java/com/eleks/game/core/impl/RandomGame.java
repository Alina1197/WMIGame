package com.eleks.game.core.impl;

import com.eleks.game.core.Game;
import com.eleks.game.core.Turn;
import com.eleks.game.entity.Room;
import com.eleks.game.enums.PlayerState;
import com.eleks.game.enums.QuestionAnswer;
import com.eleks.game.enums.RoomState;
import com.eleks.game.exception.AnswerGuessingQuestionException;
import com.eleks.game.exception.PlayerNotFoundException;
import com.eleks.game.exception.RoomNotFoundException;
import com.eleks.game.exception.TurnException;
import com.eleks.game.model.request.QuestionRequest;
import com.eleks.game.repository.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.eleks.game.components.Constants.PLAYER_NOT_FOUND;
import static com.eleks.game.components.Constants.ROOM_NOT_FOUND_BY_ID;

@Component
public class RandomGame implements Game
{
    private final List<RandomPlayer> randomPlayers;
    private final RoomRepository roomRepository;
    private final Turn currentTurn;
    private final Random random = new Random();

    public RandomGame(List<RandomPlayer> randomPlayers, RoomRepository roomRepository)
    {
        this.randomPlayers = randomPlayers;
        this.roomRepository = roomRepository;
        currentTurn = new TurnImpl(randomPlayers);
    }

    @Override
    public void startGame()
    {
        assignCharacters();
        var player = currentTurn.getGuesser();
        player.setPlayerState(PlayerState.ASK_QUESTION);
        randomPlayers
            .stream()
            .filter(randomPlayer -> !randomPlayer.getId().equals(player.getId()))
            .forEach(randomPlayer -> randomPlayer.setPlayerState(PlayerState.ANSWER_QUESTION));
    }

    @Override
    public List<RandomPlayer> getGamePLayers()
    {
        return randomPlayers;
    }

    @Override
    public Turn getTurn()
    {
        return currentTurn;
    }

    @Override
    public boolean isFinished()
    {
        return randomPlayers.size() == 1;
    }

    @Override
    public void askQuestion(String roomId, String playerId, QuestionRequest askQuestion)
    {
        Room room = checkRoomExistence(roomId);
        List<RandomPlayer> players = room.getRandomPlayers();
        cleanPlayersValues(players);
        if (room.getRoomState().equals(RoomState.GAME_IN_PROGRESS))
        {
            var askingPlayer = players
                .stream()
                .filter(randomPlayer -> randomPlayer.getId().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(String.format(PLAYER_NOT_FOUND, playerId)));

            if (askingPlayer.getPlayerState().equals(PlayerState.ASK_QUESTION))
            {
                askingPlayer.setPlayerQuestion(askQuestion.getQuestion());
                askingPlayer.setEnteredQuestion(true);
            } else
            {
                throw new TurnException("Not your turn! Current turn has player: " + room.getGame().getTurn().getGuesser().getNickname());
            }
        }
    }

    @Override
    public void answerQuestion(String roomId, String playerId, QuestionAnswer questionAnswer)
    {
        Room room = checkRoomExistence(roomId);
        List<RandomPlayer> players = room.getRandomPlayers();
        if (room.getRoomState().equals(RoomState.GAME_IN_PROGRESS))
        {
            var answerPlayer = players
                .stream()
                .filter(randomPlayer -> randomPlayer.getId().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(String.format(PLAYER_NOT_FOUND, playerId)));
            var playersAnswers = currentTurn.getPlayersAnswers();
            if (answerPlayer.getPlayerState().equals(PlayerState.ANSWER_QUESTION))
            {
                playersAnswers.add(questionAnswer);
                answerPlayer.setEnteredAnswer(true);
                answerPlayer.setPlayerAnswer(String.valueOf(questionAnswer));
            }

            if (playersAnswers.size() == players.size() - 1)
            {
                var positiveAnswers = playersAnswers
                    .stream()
                    .filter(answer -> answer.equals(QuestionAnswer.NOT_SURE) || answer.equals(QuestionAnswer.YES))
                    .collect(Collectors.toList());

                var negativeAnswers = playersAnswers
                    .stream()
                    .filter(answer -> answer.equals(QuestionAnswer.NO))
                    .collect(Collectors.toList());

                if (positiveAnswers.size() < negativeAnswers.size())
                {
                    room.getGame().getTurn().changeTurn(players);
                }
            }
        }
    }

    @Override
    public void askGuessingQuestion(String roomId, String playerId, QuestionRequest askQuestion, boolean guessStatus)
    {
        Room room = checkRoomExistence(roomId);
        List<RandomPlayer> players = room.getRandomPlayers();
        cleanPlayersValues(players);
        if (room.getRoomState().equals(RoomState.GAME_IN_PROGRESS))
        {
            var askingPlayer = players
                .stream()
                .filter(randomPlayer -> randomPlayer.getId().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(String.format(PLAYER_NOT_FOUND, playerId)));

            if (guessStatus)
            {
                askingPlayer.setPlayerState(PlayerState.GUESSING);
                askingPlayer.setPlayerQuestion(askQuestion.getQuestion());
                askingPlayer.setEnteredQuestion(true);
                askingPlayer.setGuessing(true);
            } else
            {
                throw new TurnException("Not your turn! Current turn has player: " + room.getGame().getTurn().getGuesser().getNickname());
            }
        }

    }

    @Override
    public void answerGuessingQuestion(String roomId, String playerId, QuestionAnswer questionAnswer, boolean guessStatus)
    {
        Room room = checkRoomExistence(roomId);
        List<RandomPlayer> players = room.getRandomPlayers();
        if (room.getRoomState().equals(RoomState.GAME_IN_PROGRESS))
        {
            var answerPlayer = players
                .stream()
                .filter(randomPlayer -> randomPlayer.getId().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(String.format(PLAYER_NOT_FOUND, playerId)));
            var playersAnswers = currentTurn.getPlayersAnswers();
            if (answerPlayer.getPlayerState().equals(PlayerState.ANSWER_QUESTION))
            {
                if (questionAnswer.equals(QuestionAnswer.YES) || questionAnswer.equals(QuestionAnswer.NO))
                {
                    playersAnswers.add(questionAnswer);
                } else
                {
                    throw new AnswerGuessingQuestionException("You are answering a guessing question! Answers can be only: YES or NO");
                }
            }

            if (playersAnswers.size() == players.size() - 1)
            {
                var positiveAnswers = playersAnswers
                    .stream()
                    .filter(answer -> answer.equals(QuestionAnswer.YES))
                    .collect(Collectors.toList());

                var negativeAnswers = playersAnswers
                    .stream()
                    .filter(answer -> answer.equals(QuestionAnswer.NO))
                    .collect(Collectors.toList());

                var askingPlayer = players
                    .stream()
                    .filter(randomPlayer -> randomPlayer.getPlayerState().equals(PlayerState.GUESSING))
                    .findFirst()
                    .orElseThrow(() -> new PlayerNotFoundException(String.format(PLAYER_NOT_FOUND, playerId)));

                if (positiveAnswers.size() < negativeAnswers.size())
                {
                    askingPlayer.setPlayerState(PlayerState.ANSWER_QUESTION);
                    currentTurn.changeTurn(players);
                    askingPlayer.setGuessing(false);
                } else
                {
                    room.getGame().getGamePLayers().remove(askingPlayer);
                    currentTurn.changeTurn(players);
                    askingPlayer.setPlayerState(PlayerState.GAME_WINNER);
                    room.getWinnerList().add(askingPlayer);
                }
            }
        }
    }

    private void assignCharacters()
    {
        var availableCharacters = randomPlayers.stream().map(RandomPlayer::getCharacter).collect(Collectors.toList());


        for (int i = availableCharacters.size() - 1; i >= 1; i--)
        {
            Collections.swap(availableCharacters, i, random.nextInt(i + 1));
        }

        AtomicInteger a = new AtomicInteger();
        randomPlayers.forEach(randomPlayer -> randomPlayer.setCharacter(availableCharacters.get(a.getAndIncrement())));
    }

    private Room checkRoomExistence(String roomId)
    {
        return roomRepository.findById(roomId)
            .orElseThrow(
                () -> new RoomNotFoundException(String.format(ROOM_NOT_FOUND_BY_ID, roomId)));
    }

    private void cleanPlayersValues(List<RandomPlayer> players)
    {
        players.forEach(randomPlayer -> {
            randomPlayer.setEnteredAnswer(false);
            randomPlayer.setEnteredQuestion(false);
            randomPlayer.setPlayerQuestion(null);
            randomPlayer.setPlayerAnswer(null);
        });
    }

}

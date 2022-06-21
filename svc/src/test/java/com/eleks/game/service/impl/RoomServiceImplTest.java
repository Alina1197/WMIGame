package com.eleks.game.service.impl;

import com.eleks.game.core.Game;
import com.eleks.game.enums.QuestionAnswer;
import com.eleks.game.model.request.CharacterSuggestion;
import com.eleks.game.model.request.QuestionRequest;
import com.eleks.game.model.response.RoomDetails;
import com.eleks.game.repository.RoomRepository;
import com.eleks.game.repository.impl.RoomRepositoryImpl;
import com.eleks.game.service.RoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

class RoomServiceImplTest
{
    private final RoomRepository repository = new RoomRepositoryImpl();
    private final RoomService roomService = new RoomServiceImpl(repository);
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void generalGameLoop()
    {
        var room = roomService.createRoom();
        logger.log(Level.INFO, "Current room details {0}", asJsonString(room) + "\n");
        var roomId = room.getId();

        enrollToRoom(roomId);

        logger.log(Level.INFO, "Current room details after all Players enrolled to the game {0}", asJsonString(room) + "\n");

        suggestCharacter(roomId);

        logger.log(Level.INFO, "Current room details after all Players suggest the character {0}", asJsonString(room) + "\n");

        logger.log(Level.INFO, "Game started successfully! Current turn of the player: {0} ",
            roomService.startGame(roomId).getCurrentTurn().getGuesser().getNickname() + "\n");

        logger.log(Level.INFO, "Current room details after start the game and swapping characters\n {0}", asJsonString(room) + "\n");

        var game = repository.findById(roomId).orElseThrow().getGame();

        firstTurn(roomId, game, room);

        secondTurn(roomId, game, room);

        thirdTurn(roomId, game, room);

        forthTurn(roomId, game, room);

        fifthTurn(roomId, game, room);

        sixthTurn(roomId, game, room);
    }


    private String asJsonString(RoomDetails roomDetails)
    {
        try
        {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(roomDetails);
        } catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void enrollToRoom(String roomId)
    {
        roomService.enrollToRoom(roomId, "p1");
        roomService.enrollToRoom(roomId, "p2");
        roomService.enrollToRoom(roomId, "p3");
        roomService.enrollToRoom(roomId, "p4");
    }

    private void suggestCharacter(String roomId)
    {
        roomService.suggestCharacter(roomId, "p1", new CharacterSuggestion("Vlad", "Batman"));
        roomService.suggestCharacter(roomId, "p2", new CharacterSuggestion("Bob", "Superman"));
        roomService.suggestCharacter(roomId, "p3", new CharacterSuggestion("John", "Deadpool"));
        roomService.suggestCharacter(roomId, "p4", new CharacterSuggestion("James", "Rock"));
    }

    private void firstTurn(String roomId, Game game, RoomDetails room)
    {
        logger.log(Level.INFO, "First turn : Current guesser: {0}", game.getTurn().getGuesser().getNickname() + "\n");

        game.askQuestion(roomId, "p1", new QuestionRequest("Am I a real?"));

        game.answerQuestion(roomId, "p2", QuestionAnswer.NO);
        game.answerQuestion(roomId, "p3", QuestionAnswer.NO);
        game.answerQuestion(roomId, "p4", QuestionAnswer.NOT_SURE);

        logger.log(Level.INFO, "Current room details after first turn loop\n {0}", asJsonString(room) + "\n");
    }

    private void secondTurn(String roomId, Game game, RoomDetails room)
    {
        logger.log(Level.INFO, "Second turn: Current guesser: {0}", game.getTurn().getGuesser().getNickname() + "\n");

        game.askQuestion(roomId, "p2", new QuestionRequest("Am I a real?"));

        game.answerQuestion(roomId, "p1", QuestionAnswer.YES);
        game.answerQuestion(roomId, "p3", QuestionAnswer.NO);
        game.answerQuestion(roomId, "p4", QuestionAnswer.NOT_SURE);

        logger.log(Level.INFO, "Current room details after second turn loop\n {0}", asJsonString(room) + "\n");
    }

    private void thirdTurn(String roomId, Game game, RoomDetails room)
    {
        logger.log(Level.INFO, "Third turn: Current guesser: {0} ", game.getTurn().getGuesser().getNickname() + "\n");

        game.askQuestion(roomId, "p2", new QuestionRequest("Am I a film character?"));

        game.answerQuestion(roomId, "p1", QuestionAnswer.YES);
        game.answerQuestion(roomId, "p3", QuestionAnswer.YES);
        game.answerQuestion(roomId, "p4", QuestionAnswer.YES);

        logger.log(Level.INFO, "Current room details after third turn loop\n {0}", asJsonString(room) + "\n");
    }


    private void forthTurn(String roomId, Game game, RoomDetails room)
    {
        logger.log(Level.INFO, "Fourth turn: Current guesser:  {0}", game.getTurn().getGuesser().getNickname() + "\n");

        game.askGuessingQuestion(roomId, "p2", new QuestionRequest("Am I a film character?"), true);

        game.answerGuessingQuestion(roomId, "p1", QuestionAnswer.YES, true);
        game.answerGuessingQuestion(roomId, "p3", QuestionAnswer.YES, true);
        game.answerGuessingQuestion(roomId, "p4", QuestionAnswer.YES, true);

        logger.log(Level.INFO, "Current room details after fourth turn loop and player Bob guessing\n {0}", asJsonString(room) + "\n");
    }

    private void fifthTurn(String roomId, Game game, RoomDetails room)
    {
        logger.log(Level.INFO, "Fifth turn: Current guesser:  {0}", game.getTurn().getGuesser().getNickname() + "\n");

        game.askQuestion(roomId, "p4", new QuestionRequest("Am I a women?"));

        game.answerQuestion(roomId, "p1", QuestionAnswer.NO);
        game.answerQuestion(roomId, "p3", QuestionAnswer.NO);

        logger.log(Level.INFO, "Current room details after fifth turn loop\n {0}", asJsonString(room) + "\n");
    }

    private void sixthTurn(String roomId, Game game, RoomDetails room)
    {
        logger.log(Level.INFO, "Sixth turn: Current guesser:  {0}", game.getTurn().getGuesser().getNickname() + "\n");

        game.askQuestion(roomId, "p4", new QuestionRequest("Am I a women?"));

        game.answerQuestion(roomId, "p1", QuestionAnswer.NO);
        game.answerQuestion(roomId, "p3", QuestionAnswer.NO);

        logger.log(Level.INFO, "Current room details after sixth turn loop\n {0}", asJsonString(room) + "\n");
    }

}
package com.eleks.game.controller;

import com.eleks.game.core.Game;
import com.eleks.game.enums.QuestionAnswer;
import com.eleks.game.model.request.QuestionRequest;
import com.eleks.game.model.response.TurnDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.eleks.game.utils.StringUtils.Headers.PLAYER;

@RestController
@RequestMapping("/games")
public class GameController
{
    private final Game randomGame;

    public GameController(Game randomGame)
    {
        this.randomGame = randomGame;
    }

    @GetMapping("/{id}/turn")
    public ResponseEntity<TurnDetails> findTurnInfo(@PathVariable("id") String roomId,
                                                    @RequestHeader(PLAYER) String playerId)
    {
        return ResponseEntity.ok(this.randomGame.getTurnInfo(roomId, playerId));
    }

    @PostMapping("/{id}/questions")
    public void askQuestion(@PathVariable("id") String roomId,
                            @RequestHeader(PLAYER) String playerId,
                            @RequestBody QuestionRequest askQuestion)
    {
        this.randomGame.askQuestion(roomId, playerId, askQuestion);
    }

    @PostMapping("/{id}/questions/guessing")
    public void askGuessingQuestion(@PathVariable("id") String roomId,
                                    @RequestHeader(PLAYER) String playerId,
                                    @RequestBody QuestionRequest askQuestion)
    {
        this.randomGame.askGuessingQuestion(roomId, playerId, askQuestion, true);
    }

    @PostMapping("/{id}/answer")
    public void answerQuestion(@PathVariable("id") String roomId,
                               @RequestHeader(PLAYER) String playerId,
                               @RequestParam QuestionAnswer answer)
    {
        this.randomGame.answerQuestion(roomId, playerId, answer);
    }

    @PostMapping("/{id}/answer/guessing")
    public void answerGuessingQuestion(@PathVariable("id") String roomId,
                                       @RequestHeader(PLAYER) String playerId,
                                       @RequestParam QuestionAnswer answer)
    {
        this.randomGame.answerGuessingQuestion(roomId, playerId, answer, true);
    }
}

package com.eleks.game.controller;

import com.eleks.game.model.request.CharacterSuggestion;
import com.eleks.game.model.response.PlayerDetails;
import com.eleks.game.model.response.RoomDetails;
import com.eleks.game.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.eleks.game.utils.StringUtils.Headers.PLAYER;

@RestController
@RequestMapping("/rooms")
public class RoomController
{
    private final RoomService roomService;

    public RoomController(RoomService roomService)
    {
        this.roomService = roomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoomDetails> createRoom()
    {
        return ResponseEntity.ok(this.roomService.createRoom());
    }

    @GetMapping
    public List<RoomDetails> findAvailableRooms(@RequestParam String playerId)
    {
        return this.roomService.findAvailableRooms(playerId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDetails> findById(@PathVariable("id") String roomId)
    {
        return ResponseEntity.ok(this.roomService.findRoomById(roomId));
    }

    @PostMapping("/{id}/players")
    public PlayerDetails enrollToRoom(@PathVariable("id") String roomId,
                                      @RequestHeader(PLAYER) String playerId)
    {
        return this.roomService.enrollToRoom(roomId, playerId);
    }

    @PostMapping("/{id}/characters")
    @ResponseStatus(HttpStatus.OK)
    public void suggestCharacter(@PathVariable("id") String roomId,
                                 @RequestHeader(PLAYER) String player,
                                 @Valid @RequestBody CharacterSuggestion suggestion)
    {
        this.roomService.suggestCharacter(roomId, player, suggestion);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> startGame(@PathVariable("id") String roomId)
    {
        return ResponseEntity.ok("Game started successfully! Current turn of the player: " +
            this.roomService.startGame(roomId).getCurrentTurn());
    }

//    @GetMapping("/{id}/turn")
//    public ResponseEntity<TurnDetails> findTurnInfo(@PathVariable("id") String id,
//                                                    @RequestHeader(PLAYER) String player)
//    {
//        return this.roomService.findTurnInfo(id, player)
//            .map(ResponseEntity::ok)
//            .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//
//    @PostMapping("/{id}/questions")
//    public void askQuestion(@PathVariable("id") String id,
//                            @RequestHeader(PLAYER) String player, @RequestBody Message message)
//    {
//        this.roomService.askQuestion(id, player, message.getMessage());
//    }
//
//    @PostMapping("/{id}/guess")
//    public void submitGuess(@PathVariable("id") String id,
//                            @RequestHeader(PLAYER) String player, @RequestBody Message message)
//    {
//        this.roomService.submitGuess(id, player, message.getMessage());
//    }
//
//    @PostMapping("/{id}/answer")
//    public void answerQuestion(@PathVariable("id") String id,
//                               @RequestHeader(PLAYER) String player, @RequestBody Message message)
//    {
//        this.roomService.answerQuestion(id, player, message.getMessage());
//    }
//
//    @DeleteMapping("/{id}/leave")
//    public void leaveGame(@PathVariable("id") String id,
//                          @RequestHeader(PLAYER) String player)
//    {
//        this.roomService.leaveGame(id, player);
//    }
}

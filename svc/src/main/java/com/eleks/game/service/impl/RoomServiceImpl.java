package com.eleks.game.service.impl;

import com.eleks.game.core.impl.RandomGame;
import com.eleks.game.entity.Room;
import com.eleks.game.enums.RoomState;
import com.eleks.game.exception.DuplicateRoomException;
import com.eleks.game.exception.PlayerNotFoundException;
import com.eleks.game.exception.RoomNotFoundException;
import com.eleks.game.exception.RoomStateException;
import com.eleks.game.model.request.CharacterSuggestion;
import com.eleks.game.model.response.GameDetails;
import com.eleks.game.model.response.PlayerDetails;
import com.eleks.game.model.response.RoomDetails;
import com.eleks.game.model.request.RoomRequest;
import com.eleks.game.core.impl.RandomPlayer;
import com.eleks.game.repository.RoomRepository;
import com.eleks.game.service.RoomService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.eleks.game.components.Constants.ROOM_NOT_FOUND_BY_ID;
import static com.eleks.game.components.Mapper.mapToEntity;

@Service
public class RoomServiceImpl implements RoomService
{
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository)
    {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDetails createRoom()
    {
        RoomRequest roomRequest = new RoomRequest();
        if (roomRepository.findById(roomRequest.getId()).isEmpty())
        {
            return RoomDetails.of(roomRepository.saveRoom(mapToEntity(roomRequest)));
        }
        throw new DuplicateRoomException("Room with id: " + roomRequest.getId() + " already exist!");
    }

    @Override
    public RoomDetails findRoomById(String roomId)
    {
        return RoomDetails.of(checkRoomExistence(roomId));
    }

    @Override
    public GameDetails startGame(String roomId)
    {
        Room room = checkRoomExistence(roomId);
        List<RandomPlayer> randomPlayers = room.getRandomPlayers();
        switch (room.getRoomState())
        {
            case GAME_IN_PROGRESS:
                throw new RoomStateException("Game already in progress! Find another one to play!");
            case READY_TO_PLAY:
                if (randomPlayers
                    .stream()
                    .filter(RandomPlayer::isSuggestStatus)
                    .count() == 4)
                {
                    var randomGame = new RandomGame(randomPlayers, roomRepository);
                    randomGame.startGame();
                    room.setGame(randomGame);
                    room.setRoomState(RoomState.GAME_IN_PROGRESS);
                    return GameDetails.of(randomGame);
                }
                break;
            case SUGGEST_CHARACTER:
                throw new RoomStateException("Game can not be started! Players suggesting characters! " +
                    "Waiting for other players to contribute their characters" +
                    "Players left: " +
                    randomPlayers
                        .stream()
                        .filter(randomPlayer -> !randomPlayer.isSuggestStatus())
                        .map(RandomPlayer::getNickname)
                        .collect(Collectors.toList()));
            case WAITING_FOR_PLAYERS:
                throw new RoomStateException("Game can not be started!" +
                    " Waiting for additional players! " +
                    "Current players number: " + randomPlayers.size() + "/4");
        }
        return null;
    }

    @Override
    public PlayerDetails enrollToRoom(String roomId, String playerId)
    {
        Room room = checkRoomExistence(roomId);
        RandomPlayer player;
        if (room.getRoomState().equals(RoomState.WAITING_FOR_PLAYERS))
        {
            var playersInRoom = room.getRandomPlayers();
            if (playersInRoom.stream().noneMatch(randomPlayer -> randomPlayer.getId().equals(playerId)))
            {
                player = new RandomPlayer(playerId, roomId, "Player-" + (playersInRoom.size() + 1));
                playersInRoom.add(player);
                if (playersInRoom.size() == 4)
                {
                    room.setRoomState(RoomState.SUGGEST_CHARACTER);
                }
                return PlayerDetails.of(player);
            } else
            {
                throw new RoomStateException("Player already enrolled in this room!");
            }
        }
        throw new RoomStateException("You cannot enroll to this room! Room is full!");
    }

    @Override
    public List<RoomDetails> findAvailableRooms(String playerId)
    {
        return roomRepository.findAllRooms(playerId).stream()
            .filter(room -> room.getRoomState().equals(RoomState.WAITING_FOR_PLAYERS))
            .map(RoomDetails::of)
            .collect(Collectors.toList());
    }

    @Override
    public void suggestCharacter(String roomId, String headerId, CharacterSuggestion suggestion)
    {
        Room room = checkRoomExistence(roomId);
        if (room.getRoomState().equals(RoomState.SUGGEST_CHARACTER))
        {
            var players = room.getRandomPlayers();
            var player = players
                .stream()
                .filter(randomPlayer -> randomPlayer.getId().equals(headerId))
                .findFirst().orElseThrow(() -> new PlayerNotFoundException("Player not found!"));
            if (!player.isSuggestStatus())
            {
                player.setCharacter(suggestion.getCharacter());
                player.setNickname(suggestion.getNickname());
                player.setSuggestStatus(true);
            } else
            {
                throw new RoomStateException("You already suggest the character");
            }

            if (players.stream().filter(RandomPlayer::isSuggestStatus).count() == 4)
            {
                room.setRoomState(RoomState.READY_TO_PLAY);
            }
        } else
        {
            throw new RoomStateException("You cannot suggest the character! Current game state is: " + room.getRoomState());
        }
    }

    @Override
    public void leaveRoom(String roomId, String playerId)
    {
        Room room = checkRoomExistence(roomId);
        var roomPlayers = room.getRandomPlayers();
        roomPlayers.stream()
            .filter(randomPlayer -> randomPlayer.getId().equals(playerId))
            .collect(Collectors.toList())
            .forEach(roomPlayers::remove);
    }

    private Room checkRoomExistence(String roomId)
    {
        return roomRepository.findById(roomId)
            .orElseThrow(
                () -> new RoomNotFoundException(String.format(ROOM_NOT_FOUND_BY_ID, roomId)));
    }
}

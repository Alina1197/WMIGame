package com.eleks.game.service;

import com.eleks.game.model.request.CharacterSuggestion;
import com.eleks.game.model.response.GameDetails;
import com.eleks.game.model.response.PlayerDetails;
import com.eleks.game.model.response.RoomDetails;

import java.util.List;

public interface RoomService
{
    RoomDetails createRoom();

    RoomDetails findRoomById(String roomId);

    GameDetails startGame(String roomId);

    PlayerDetails enrollToRoom(String roomId, String player);

    List<RoomDetails> findAvailableRooms(String playerId);

    void suggestCharacter(String roomId, String playerNickname, CharacterSuggestion suggestion);

    void leaveRoom(String roomId, String player);
}

package com.tictactoeAI.tictactoe_backend.controller;

import com.tictactoeAI.tictactoe_backend.engine.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/game")
public class TicTacToeController {

    private final Map<String, GameSession> sessions = new ConcurrentHashMap<>();

    public String home() {
        return "Tic Tac Toe API is running!";
    }
    @GetMapping("/new")
    public Map<String, String> newGame(@RequestParam(defaultValue = "easy") String aiLevel,
                                       @RequestParam(defaultValue = "X") String playerSymbol) {
        String id = UUID.randomUUID().toString();
        GameSession session = new GameSession(aiLevel.toLowerCase(), playerSymbol.charAt(0));
        sessions.put(id, session);
        return Map.of("sessionId", id);
    }

    @PostMapping("/move")
    public Map<String, Object> makeMove(@RequestParam String sessionId,
                                        @RequestParam int row,
                                        @RequestParam int col) {
        GameSession session = sessions.get(sessionId);
        if (session == null) {
            return Map.of("error", "Invalid session ID");
        }
        return session.makePlayerMove(row, col);
    }

    @GetMapping("/state")
    public Map<String, Object> getState(@RequestParam String sessionId) {
        GameSession session = sessions.get(sessionId);
        if (session == null) {
            return Map.of("error", "Invalid session ID");
        }
        return session.getBoardState();
    }
}

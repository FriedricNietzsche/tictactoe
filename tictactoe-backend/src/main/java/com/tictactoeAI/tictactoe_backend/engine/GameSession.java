package com.tictactoeAI.tictactoe_backend.engine;


import com.tictactoeAI.tictactoe_backend.tictactoe.AiLevelEasy;
import com.tictactoeAI.tictactoe_backend.tictactoe.AiLevelMedium;
import com.tictactoeAI.tictactoe_backend.tictactoe.Grid;
import com.tictactoeAI.tictactoe_backend.tictactoe.MinimaxAI;

import java.util.*;

public class GameSession {

    private final Grid grid;
    private final char playerSymbol;
    private final char aiSymbol;
    private final Object ai;

    public GameSession(String aiLevel, char playerSymbol) {
        this.grid = new Grid();
        this.playerSymbol = playerSymbol;
        this.aiSymbol = (playerSymbol == 'X') ? 'O' : 'X';

        switch (aiLevel) {
            case "medium":
                this.ai = new AiLevelMedium();
                break;
            case "hard":
                this.ai = new MinimaxAI(aiSymbol);
                break;
            case "easy":
            default:
                this.ai = new AiLevelEasy();
        }



    }

    public Map<String, Object> makePlayerMove(int row, int col) {
        Map<String, Object> response = new HashMap<>();

        if (grid.isCellOccupied(row, col)) {
            response.put("error", "Cell is already occupied");
            response.put("board", getBoard()); // always return a board
            return response;
        }

        grid.placeValue(row, col, playerSymbol);

        if (grid.checkForWinner() == playerSymbol) {
            response.put("winner", playerSymbol);
        } else if (grid.checkForDraw()) {
            response.put("draw", true);
        } else {
            // AI makes a move
            if (ai instanceof AiLevelEasy) {
                ((AiLevelEasy) ai).makeMove(grid, aiSymbol);
            } else if (ai instanceof AiLevelMedium) {
                ((AiLevelMedium) ai).makeMove(grid, aiSymbol);
            } else if (ai instanceof MinimaxAI) {
                ((MinimaxAI) ai).makeMove(grid);
            }

            if (grid.checkForWinner() == aiSymbol) {
                response.put("winner", aiSymbol);
            } else if (grid.checkForDraw()) {
                response.put("draw", true);
            }
        }

        response.put("board", getBoard()); // always include board
        return response;
    }

    public Map<String, Object> getBoardState() {
        Map<String, Object> state = new HashMap<>();
        state.put("board", getBoard());
        state.put("winner", grid.checkForWinner());
        state.put("draw", grid.checkForDraw());
        return state;
    }

    private String[][] getBoard() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = String.valueOf(grid.readValue(i, j));
            }
        }
        return board;
    }
}

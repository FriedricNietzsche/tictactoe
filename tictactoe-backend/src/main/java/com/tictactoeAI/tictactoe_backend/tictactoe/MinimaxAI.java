package com.tictactoeAI.tictactoe_backend.tictactoe;

import com.tictactoeAI.tictactoe_backend.tictactoe.Grid;

public class MinimaxAI {
    private final char player;
    //Initialize MinimaxAi with the player 'X' or 'O'.
    public MinimaxAI(char player){
        this.player = player;
    }


    public void makeMove(Grid grid){
        int bestScore = Integer.MIN_VALUE;
        int bestRow = 0;
        int bestColoumn =0;

        // Iterate through each cell on the grid
        for(int i = 0; i < 3 ; i++){
            for( int j =0 ; j < 3 ; j++){
                // Is spot available?
                if(!grid.isCellOccupied(i,j)){
                    // temporally place symbol in spot
                    grid.placeValue(i,j,player);
                    //calculate score for current move using minimax algorithm
                    int score = miniMax(grid, 0, false);
                    //undo temp placement
                    grid.placeValue(i,j,'_');

                    //update bestscore and bestmove with current score and move if higher score
                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = i;
                        bestColoumn = j;
                    }

                }


            }
        }

        // place best move
        grid.placeValue(bestRow,bestColoumn, player);

    }

    public int miniMax(Grid grid, int depth, boolean isMaximizing) {
        char opponent = (player == 'X') ? 'O' : 'X';

        // best cases for minimax algorithm
        if (grid.checkForWinner() == player)
            return 1;
        else if (grid.checkForWinner() == opponent)
            return -1;
        else if (grid.checkForDraw())
            return 0;
        //recursive algorithm
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!grid.isCellOccupied(i, j)) {
                        grid.placeValue(i, j, player);
                        int currentScore = miniMax(grid, depth + 1, false);
                        grid.placeValue(i, j, '_');
                        bestScore = Math.max(bestScore, currentScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!grid.isCellOccupied(i, j)) {
                        grid.placeValue(i, j, opponent);
                        int currentScore = miniMax(grid, depth + 1, true);
                        grid.placeValue(i, j, '_');
                        bestScore = Math.min(bestScore, currentScore);
                    }
                }
            }
            return bestScore;
        }

    }


}


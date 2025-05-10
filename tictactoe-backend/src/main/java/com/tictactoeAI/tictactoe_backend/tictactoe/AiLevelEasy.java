package com.tictactoeAI.tictactoe_backend.tictactoe;

import java.util.Random;

public class AiLevelEasy  {
    // creating random object to generate random numbers for the
    //3x3 tictactoe array for the easy array.
    private Random random;

    public AiLevelEasy() {
        random = new Random();
    }

    
    public void makeMove(Grid grid, char symbol) {
        int row, col;

        // Keep generating random row and column until an empty cell is found
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (grid.isCellOccupied(row, col));

        grid.placeValue(row, col, symbol);
    }

}

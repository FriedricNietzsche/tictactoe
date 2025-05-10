package com.tictactoeAI.tictactoe_backend.tictactoe;

public class Grid {

    private char[][] grid;

    public Grid() {
        // Creating 2d 3x3 array for the grid and intilizing each spot with
        // char value '_' for empty spot, 'X' for X and 'O' for o.
        grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = '_';
            }
        }
    }

    public void placeValue(int row, int column, char value) {

        // checks if cords given as parameter are valid and then places char value
        // inside array
        if (row < 0 | column < 0 | row > this.grid.length | column > this.grid.length) {
            return;
        }

        this.grid[row][column] = value;
    }

    public char readValue(int row, int column) {
       // checks if cords given as parameter are valid then returns value
        // in array from given cords.
        if (row < 0 || row > 3 || column < 0 || column > 3) {
            return 'L';
        }
        return grid[row][column];
    }

    public char checkForWinner() {
        // checking if 3 consecutive X or O are inside the array
        // Using 8 if-else statements and returns the char that won
        // else returns 1

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (this.grid[0][i] == 'X' || this.grid[0][i] == 'O') {
                char symbol = this.grid[0][i];
                if (this.grid[1][i] == symbol && this.grid[2][i] == symbol) {
                    return symbol;
                }
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (this.grid[i][0] == 'X' || this.grid[i][0] == 'O') {
                char symbol = this.grid[i][0];
                if (this.grid[i][1] == symbol && this.grid[i][2] == symbol) {
                    return symbol;
                }
            }
        }

        // Check diagonals
        if (this.grid[0][0] == 'X' || this.grid[0][0] == 'O') {
            if (this.grid[0][0] == this.grid[1][1] && this.grid[1][1] == this.grid[2][2]) {
                return this.grid[0][0];
            }
        }
        if (this.grid[0][2] == 'X' || this.grid[0][2] == 'O') {
            if (this.grid[0][2] == this.grid[1][1] && this.grid[1][1] == this.grid[2][0]) {
                return this.grid[0][2];
            }
        }


        return '1';

    }

    public boolean checkForDraw() {
       // checking to see if there are any unoccupied cells.
        // if found then no draw, else there is a draw
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 'X' && grid[i][j] != 'O') {
                    return false; // found unoccupied cell
                }
            }
        }
        return true; //  occupied cells found
    }

    public boolean isCellOccupied(int row, int column) {
        // checking to see if cell inside the array contains a char value X or O.
        // If it does it returns true else it returns false
        if (this.grid[row][column] == 'X' || this.grid[row][column] == 'O') {
            return true;
        } else if (this.grid[row][column] == '_') {
            return false;
        }

        return false;
    }

    public void print() {
        // Method for Console UserInterface to print board
        System.out.println("---------"); // top border
        for (int row = 0; row < 3; row++) {
            System.out.print("| "); // left border
            for (int column = 0; column < 3; column++) {
                if (grid[row][column] == '_') {
                    System.out.print("  ");
                    continue;
                }
                System.out.print(grid[row][column] + " ");
            }
            System.out.print("|\n"); // right border

        }
        System.out.println("---------"); // bottom border


    }
}

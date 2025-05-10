package com.tictactoeAI.tictactoe_backend.tictactoe;

import java.util.Random;

public class AiLevelMedium {

    private int row;
    private int column;

    public void makeMove(Grid grid, char value){

        // Checking entire grid to see if 2 consecutive X's or O's are placed


        if(grid.checkForDraw()){
            System.out.println("test");
            return;
        } else if( grid.checkForWinner() == 'X' || grid.checkForWinner() == 'O'){
            return;
        }

        // checking rows for 2 values in a row XX_
        for(int i  = 0 ; i < 3 ; i ++){

            int j = 0;

                if(grid.readValue(i, j)==grid.readValue(i,j+1)&& !grid.isCellOccupied(i, j + 2)&& grid.readValue(i,j+1)!='_'){

                    grid.placeValue(i, j+2,value);
                    return;
                } else{
                    continue;
                }


        }

        // _XX
        for(int i  = 0 ; i < 3 ; i ++){

            int j = 0;

            if(grid.readValue(i, j+2)==grid.readValue(i,j+1)&& !grid.isCellOccupied(i, j)&& grid.readValue(i,j+1)!='_'){

                grid.placeValue(i, j,value);
                return;
            } else{
                continue;
            }


        }




        // checking for columns


            int i = 0 ;
            for(int j =0 ; j < 3 ; j++){

                if(grid.readValue(i, j)==grid.readValue(i+1,j)&& !grid.isCellOccupied(i+2,j)&& grid.readValue(i+1,j)!='_'){

                    grid.placeValue(i+2, j,value);
                    return;
                } else{
                    continue;
                }


            }

         i = 0 ;
        for(int j =0 ; j < 3 ; j++){

            if(grid.readValue(i+2, j)==grid.readValue(i+1,j)&& !grid.isCellOccupied(i,j)&& grid.readValue(i+1,j)!='_'){

                grid.placeValue(i, j,value);
                return;
            } else{
                continue;
            }


        }



            // checking for digonals
        if(grid.readValue(0,0)==grid.readValue(1,1) && !grid.isCellOccupied(2,2) && grid.readValue(1,1)!='_'){

            grid.placeValue(2,2,value);
            return;
        }

        if(grid.readValue(2,2)==grid.readValue(1,1) && !grid.isCellOccupied(0,0)&& grid.readValue(1,1)!='_'){

            grid.placeValue(0,0,value);
            return;
        }

        if(grid.readValue(0,2)==grid.readValue(1,1) && !grid.isCellOccupied(2,0)&& grid.readValue(1,1)!='_'){

            grid.placeValue(2,0,value);
            return;
        }

        if(grid.readValue(2,0)==grid.readValue(1,1) && !grid.isCellOccupied(0,2)&& grid.readValue(1,1)!='_'){

            grid.placeValue(0,2,value);
            return;
        }

        Random random = new Random ();
        while(true){
            int row = random.nextInt(3);
            int column = random.nextInt(3);
            if(grid.isCellOccupied(row, column)){
                continue;
            }
            grid.placeValue(row,column,value);
            break;
        }

        //grid.print();

    }

}

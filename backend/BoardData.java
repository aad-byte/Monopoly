package backend;

public class BoardData {
    BoardTile[][] boardTilesCollection = new BoardTile[9][]; //5 types of properties, Train Station, Generic Squares, Jail tile, chance tile

        {//figure this out, like how to export code into main
             //intatlize arrays for properties
        for(int i = 0; i < 2; i++){
            boardTilesCollection[i] = new Property[3];
        }
       

        //prp type 1
        boardTilesCollection[0][1] = new Property("States Avenue", 140, 5, 0, 0);
        boardTilesCollection[0][2] = new Property("St.Charles Avenue", 140, 7, 0, 0);
        boardTilesCollection[0][0] = new Property("Virginia Avenue", 160, 8, 0, 0);

        //prp type 2
        boardTilesCollection[1][1] = new Property("Ventinor Avenue", 260, 14, 0, 1);
        boardTilesCollection[1][2] = new Property("Atlantic Avenue " , 260,17, 0, 1);
        boardTilesCollection[1][0] = new Property("Marvin Avenue", 280, 19, 0, 1);

         //explain what ur initalizing
         for(int i = 2; i < 5; i++){
            boardTilesCollection[i] = new Property[2];
        }

        //prp type 3 
        boardTilesCollection[2][0] = new Property("Mediteranean Avenue", 60, 1, 0, 2);
        boardTilesCollection[2][1] = new Property("Baltic Avenue ", 60, 4, 0, 2);

        //prp 4
        boardTilesCollection[3][1] = new Property("North Carolina Avenue", 300, 10, 0, 3);
        boardTilesCollection[3][0] = new Property("Pennsylvania Avenue ", 320, 13, 0, 3);

        //prp type 5
        boardTilesCollection[4][1] = new Property("Park Place", 350, 22, 0, 4);
        boardTilesCollection[4][0] = new Property("Boardwalk ", 400, 23, 0, 4);

        boardTilesCollection[5] = new Property[4];
        //train station
        boardTilesCollection[5][0] = new Property("Train Station South", 3, 0, 0, 5);
        boardTilesCollection[5][1] = new Property("Train Station West", 9, 0, 0, 5);
        boardTilesCollection[5][2] = new Property("Train Station North", 15, 0, 0, 5);
        boardTilesCollection[5][3] = new Property("Train Station East", 21, 0, 0, 5);

        //Generic
        boardTilesCollection[6] = new Generic[3];
        boardTilesCollection[6][0] = new Generic(0, 200, "Go Square");
        boardTilesCollection[6][1] = new Generic(6, 0, "Jail");
        boardTilesCollection[6][2] = new Generic(12, 0, "Park");
        //chance cards
        boardTilesCollection[7] = new Chance[4];
        boardTilesCollection[7][0] = new Chance(2);
        boardTilesCollection[7][1] = new Chance(11);
        boardTilesCollection[7][2] = new Chance(16);
        boardTilesCollection[7][3] = new Chance(20);
        //jail
        boardTilesCollection[8] = new Jail[1];
        boardTilesCollection[8][0] = new Jail(18);

        //GO Square
        }

}

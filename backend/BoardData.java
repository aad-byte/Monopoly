package backend;

public class BoardData{
    public BoardTile[][] tiles = new BoardTile[9][]; //5 types of properties, Train Station, Generic Squares, Jail tile, chance tile

        public BoardData(){//figure this out, like how to export code into main
             //intatlize arrays for properties
        for(int i = 0; i < 2; i++){
            tiles[i] = new Property[3];
        }
       
        int cellSize = 52; //size of 1 grid in pixels

        //prp type 1
        tiles[0][1] = new Property("States Avenue", 140, 5, 9, 0, (cellSize*3)/2, cellSize*4);
        tiles[0][2] = new Property("St.Charles Avenue", 140, 7, 9, 0, cellSize*4, (cellSize*3)/2);
        tiles[0][0] = new Property("Virginia Avenue", 160, 8, 10, 0, cellSize*6, (cellSize*3)/2);

        //prp type 2
        tiles[1][1] = new Property("Ventinor Avenue", 260, 14, 21, 1, (cellSize*29)/2, cellSize*6);
        tiles[1][2] = new Property("Atlantic Avenue " , 260,17, 21, 1, (cellSize*29)/2, cellSize*12);
        tiles[1][0] = new Property("Marvin Avenue", 280, 19, 23, 1, cellSize*12, (cellSize*29)/2);

         //explain what ur initalizing
         for(int i = 2; i < 5; i++){
            tiles[i] = new Property[2];
        }

        //prp type 3 
        tiles[2][0] = new Property("Mediteranean Avenue", 60, 1, 2, 2, (cellSize*3)/2, cellSize*12);
        tiles[2][1] = new Property("Baltic Avenue ", 60, 4, 2, 2, (cellSize*3)/2, cellSize*6);

        //prp 4
        tiles[3][1] = new Property("North Carolina Avenue", 300, 10, 26, 3, cellSize*10, (cellSize*3)/2);
        tiles[3][0] = new Property("Pennsylvania Avenue ", 320, 13, 28, 3, (cellSize*29)/2, cellSize*4);

        //prp type 5
        tiles[4][1] = new Property("Park Place", 350, 22, 20, 4, cellSize*6, (cellSize*29)/2);
        tiles[4][0] = new Property("Boardwalk ", 400, 23,  30, 4, cellSize*4, (cellSize*29)/2);

        tiles[5] = new Property[4];
        //train station
        tiles[5][0] = new Property("Train Station West", 200, 3, 25, 5, (cellSize*3)/2, cellSize*8);
        tiles[5][1] = new Property("Train Station North", 200, 9, 25, 5, cellSize*8, (cellSize*3)/2);
        tiles[5][2] = new Property("Train Station East", 200, 15, 25, 5, (cellSize*29)/2, cellSize*8);
        tiles[5][3] = new Property("Train Station South", 200, 21, 25, 5, cellSize*8, (cellSize*29)/2);

        //Generic
        tiles[6] = new Generic[3];
        tiles[6][0] = new Generic(0, 200, "Go Square", (cellSize*3)/2, (cellSize*29)/2);
        tiles[6][1] = new Generic(6, 0, "Jail", (cellSize*3)/2, (cellSize*3)/2);
        tiles[6][2] = new Generic(12, 0, "Free Parking", (cellSize*29)/2 ,(cellSize*3)/2);
        //chance cards
        tiles[7] = new Chance[4];
        tiles[7][0] = new Chance(2, (cellSize*3)/2, cellSize*12);
        tiles[7][1] = new Chance(11, cellSize*12, (cellSize*3)/2);
        tiles[7][2] = new Chance(16, (cellSize*29)/2, cellSize*10);
        tiles[7][3] = new Chance(20, cellSize * 10, (cellSize*29)/2);
        //jail
        tiles[8] = new Jail[1];
        tiles[8][0] = new Jail(18, (cellSize*29)/2, (cellSize*29)/2);

        //GO Square
        }

}

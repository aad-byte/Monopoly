package backend;

public class BoardData {
    BoardTile[][] boardTilesCollection = new BoardTile[9][]; //Jail tile, chance tile, 5 types of proerties, trainstation, GO square

        {//figure this out, like how to export code into main
             //intatlize arrays for properties
        for(int i = 0; i < 2; i++){
            boardTilesCollection[i] = new Property[3];
        }

        for(int i = 2; i < 5; i++){
            boardTilesCollection[i] = new Property[2];
        }
        boardTilesCollection[4] = new Property[4];

        //prp type 1
        boardTilesCollection[0][1] = new Property("States Avenue", 140, 0, 0, 0);
        boardTilesCollection[0][2] = new Property("St.Charles Avenue", 140, 0, 0, 0);
        boardTilesCollection[0][0] = new Property("Virginia Avenue", 160, 0, 0, 0);

        //prp type 2
        boardTilesCollection[1][1] = new Property("Ventinor Avenue", 260, 0, 0, 1);
        boardTilesCollection[1][2] = new Property("Atlantic Avenue " , 260, 0, 0, 1);
        boardTilesCollection[1][0] = new Property("Marvin Avenue", 280, 0, 0, 1);

        //prp type 3 
        boardTilesCollection[2][0] = new Property("Mediteranean Avenue", 60, 0, 0, 2);
        boardTilesCollection[2][1] = new Property("Baltic Avenue ", 60, 0, 0, 2);

        //prp 4
        boardTilesCollection[3][1] = new Property("North Carolina Avenue", 300, 0, 0, 3);
        boardTilesCollection[3][0] = new Property("Pennsylvania Avenue ", 320, 0, 0, 3);

        //prp type 5
        boardTilesCollection[4][1] = new Property("Park Place", 350, 0, 0, 4);
        boardTilesCollection[4][0] = new Property("Boardwalk ", 400, 0, 0, 4);

        //train station
        boardTilesCollection[5][0] = new Property("Train Station South", 0, 0, 0, 5);
        boardTilesCollection[5][1] = new Property("Train Station West", 0, 0, 0, 5);
        boardTilesCollection[5][2] = new Property("Train Station North", 0, 0, 0, 5);
        boardTilesCollection[5][3] = new Property("Train Station East", 0, 0, 0, 5);

        //chance cards

        //jail

        //GO Square
        }

}

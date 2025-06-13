package backend;

public class BoardData {
    BoardTile[][] boardTilesCollection = new BoardTile[8][]; //Jail tile, chance tile, 4 types of proerties, trainstation, GO square

        {
             //intatlize arrays for properties
        for(int i = 0; i < 4; i++){
            boardTilesCollection[i] = new Property[3];
        }
        boardTilesCollection[4] = new Property[4];

        //prp type 1
        boardTilesCollection[0][0] = new Property(null, 0, 0, 0, 0);
        boardTilesCollection[0][1] = new Property(null, 0, 0, 0, 0);
        boardTilesCollection[0][2] = new Property(null, 0, 0, 0, 0);

        //prp type 2
        boardTilesCollection[1][0] = new Property(null, 0, 0, 0, 0);
        boardTilesCollection[1][1] = new Property(null, 0, 0, 0, 0);
        boardTilesCollection[1][2] = new Property(null, 0, 0, 0, 0);

        //prp type 3 
        boardTilesCollection[2][0] = new Property(null, 0, 0, 0, 0);
        boardTilesCollection[2][1] = new Property(null, 0, 0, 0, 0);
        boardTilesCollection[2][2] = new Property(null, 0, 0, 0, 0);

        //prp 4
        boardTilesCollection[3][0] = new Property(null, 0, 0, 0, 0);
        boardTilesCollection[3][1] = new Property(null, 0, 0, 0, 0);
        boardTilesCollection[3][2] = new Property(null, 0, 0, 0, 0);

        //train station
        boardTilesCollection[4][0] = new Property(null, 0, 0, 0, 0);
        boardTilesCollection[4][1] = new Property(null, 0, 0, 0, 0);
        boardTilesCollection[4][2] = new Property(null, 0, 0, 0, 0);
        boardTilesCollection[4][3] = new Property(null, 0, 0, 0, 0);

        //chance cards

        //jail

        //GO Square
        }

}

package backend;

public class BoardTile {
    private final int position;

    public BoardTile(int position){
        this.position = position;
    }

    public int getPosition(){
        return position;
    }

    //method to retrive board class object reference based on position on board

    public static BoardTile getTile(int position, BoardData board){
    BoardTile tile = null;
    for(int i = 0; i < board.tiles.length; i++){
        for(int j = 0; j < board.tiles[i].length; j++){
            if(board.tiles[i][j].getPosition() == position){
                tile = board.tiles[i][j];
            }
        }
    }

    return tile;
}
}

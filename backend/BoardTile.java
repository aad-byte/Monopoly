package backend;

public class BoardTile {
    private final int position;
    private final int GridX;
    private final int GridY;

    public BoardTile(int position, int x, int y){
        this.position = position;
        this.GridX = x;
        this.GridY = y;
    }

    public int getPosition(){
        return position;
    }

    //methods to retrive pixel coordinates
    public int getX(){
        return GridX;
    }

    public int getY(){
        return GridY;
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

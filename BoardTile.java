
public class BoardTile {
	
	private final int position;
    public BoardTile(int position){
        this.position = position;
    }

    public int getPosition(){
        return position;
    }
    
    public static BoardTile getTile(int position, BoardTile [][] tiles){
        BoardTile tileToReturn = null;
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                if(tiles[i][j].getPosition() == position){
                    tileToReturn = tiles[i][j];
                }
            }
        }
        return tileToReturn;
    }
	  

}

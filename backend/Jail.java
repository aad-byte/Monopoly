package backend;

public class Jail extends BoardTile{
    
    public Jail(int position, int x, int y){
        super(position, x, y);
    }

    public static void sendJail(Player player){
        player.inJail = true;
        player.position = 6; //Change player positon to the visiting Jail Board (figure out a slightly diff animation for this movement)
        player.roundsLeftJail += 2;
    }
}

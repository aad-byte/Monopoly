
public class Jail extends BoardTile {
	    
    public Jail(int position){
        super(position);
    }

    public static void sendJail(Player player){
        player.inJail = false;
        player.position = 6; //Change player position to the visiting Jail Board (figure out a slightly diff animation for this movement)
        player.roundsLeftJail = 2;
	}
    
}

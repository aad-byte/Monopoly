import java.util.Random;
public class Chance extends BoardTile{
	
	public Chance(int position) {
		super(position);
	}
	
	//Actual Constructor
	//public Chance(int position, int x, int y) {
    // super(position, x, y);
	//}

	private static String instruction;
	
	public static void performAction(int code, Player player, BoardData board) {
		Random rand = new Random();
		
		//Type #1: Add/Deduct Cash 
		
		//Checking if code is one or if code is 4 and the player doesn't own any properties
		if(code==1 || (code==4 && player.getList().owned==0)) {
			instruction = "";
			double amount; //Double to hold amount to be generated
			
			do { amount=(double)(rand.nextInt(81)*5)-200; //Generating random amount between -200 and +200 that is a multiple of 5.
			}while(Math.abs(amount)>=50); //Ensuring that the player gains or loses at least $50 
			
			player.editCash(amount); //Adding/deducting amount from player's cash
			
			//Updating instruction
			if(amount>0) instruction="Gain $"+amount+"!"; 
			else  instruction="Lose $"+(amount)*-1+"!";
		}
		
		//Type #2:  Send to Jail 
		else if(code==2) {
			Jail.sendJail(player); //Sending player to jail
			instruction="GO TO JAIL!"; //Updating instruction
		}
		
		//Type #3: Alter Position
		else if (code==3){
			int posToMove=rand.nextInt(5)+5;//Generates a position between 5-9 inclusive

            instruction="Move up "+posToMove+" positions!"; //Updating instruction

			posToMove=(player.location.getPosition()+posToMove)%24; //% 24 allows player to pass GO tile if they're at the end of the board
			BoardTile tile = getTile(posToMove, board); //Getting the board tile at the new location
			player.updatePosition(tile, posToMove, player.latestDiceRoll); //Updating position of the player.	
		}
		
		//Type #4: Surrendering property
		else {
			int type; //Variable to store type of property the player must surrender
			int pos; //Variable to hold the position on the list where the property of generated type is found
			do {
				type = rand.nextInt(5)+1; //Generates a random # between 1-5 inclusive to represent type of property to surrender
				pos = player.getList().search(type); //Updating position
			}while(pos==-1); //Type generation continues until that type of property exists in the player's list
	
			Property deleted= player.getList().delete(pos); //Deleting the property at the list 
			deleted.setOwner(null); //Setting the owner of the deleted property to null
			deleted.setOwned(false); //Updating ownership status of the deleted property
			
			instruction = "Surrender Property #"+pos+" of your collection!"; //Updating instruction
		}
	}
	
    public static String returnInstruction() {
		return instruction; //Returning the updated instruction to main
	}

}
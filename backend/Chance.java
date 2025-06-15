package backend;

import java.util.Random;
public class Chance extends BoardTile{
	private static String instruction;
	
	public Chance(int position) {
		super(position);
	}

	public static void performAction(int code, Player player, BoardData board) {
		Random rand = new Random();
		
		//Type #1: Add/Deduct Cash
		if(code==1) {
			instruction = "";
			double amount;
			do {
				amount=(rand.nextInt(401)-200)/5;
			}while(Math.abs(amount)>50);
			
			amount= (double) -200 + (amount*5);
			player.editCash(amount);
			
			if(amount>0) instruction="Gain $"+amount+"!";
			else  instruction="Lose $"+(amount)*-1+"!";
		}
		
		//Type #2:  Send to Jail 
		else if(code==1002) {
			Jail.sendJail(player);
			instruction="GO TO JAIL!";
		}
		
		//Type #3: Alter Position
		else{
			int posToMove=rand.nextInt(5)+5; //get the number of spaces to move

            instruction="Move up "+posToMove+" positions!";

			posToMove=(player.location.getPosition()+posToMove)%24; //must do mod 24 
			BoardTile tile = getTile(posToMove, board);
			player.updatePosition(tile, posToMove, player.latestDiceRoll);
			
		}
	}
	
	public static void displayInstruction() {
		System.out.println(instruction);
	}

}

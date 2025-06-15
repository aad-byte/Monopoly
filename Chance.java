import java.util.Random;
public class Chance extends BoardTile{
	private static String instruction;
	
	public Chance(int position) {
		super(position);
	}

	public static void performAction(int code, Player player) {
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
			int posToMove=rand.nextInt(21)+5;
			posToMove=player.location.getPosition()+posToMove;
			BoardTile tile =getTile(posToMove, Player.playersGameBoard);
			player.updatePosition(tile, posToMove);
			
			instruction="Move up "+posToMove+" positions!";
		}
	}
	
	public static void displayInstruction() {
		System.out.println(instruction);
	}

	/*private static BoardTile searchTile(BoardTile[][] arr, int moveTile) {
		for(int i=0; i<arr.length; i++) {
			
		}
	}*/
	
	/*public String displayInstruction() {
		return instruction;
	}
	
	public int getCode() {
		return code;
	}*/
	
	/*Chance(String instruction, int code){
	this.instruction=instruction;
	this.code=code;
}

Chance(String instruction, int max, int min){
	this.instruction=instruction;
	this.code=1002;
	double amount;
	Random rand = new Random();
	do {
		amount=rand.nextInt(((max-min)/10)+1);
	}while(amount!=0);
	
	amount= (double) min + amount*10;
}*/
	
}

import java.util.Scanner;

import java.util.Random;

public class Main {
	
	public static Random rand = new Random();
	private static int roundsRemainingInJail;

	//Method to generate random dice roll
	public static int rollDice() {
		int diceRoll= (rand.nextInt(6)) + 1;
		return diceRoll;
	}
	
	//Aadya works on this
	public static Player[] playerOrder(Player[] list){
	    int unsortedLength = list.length -1;
	    Player temp;
	    for(int i = unsortedLength; i > 0; i--){
	        for(int j = 0; j < unsortedLength; j++){
	            if(list[j].latestDiceRoll < list[j+1].latestDiceRoll ){
	                temp = list[j];
	                list[j] = list[j+1];
	                list[j+1] = temp;
	            }
	        }
	        unsortedLength -= 1;
	    }
	    return list;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Welcome to Online Monopoly!");
		Player [] player = new Player[3];
		String name;
		
		for(int i=0; i<3; i++) {
			System.out.print("Player #"+(i+1)+", enter your name: ");
			name = in.nextLine();
			player[i]=new Player(name);
		}
		
		//CREATE BOARD TILES ARRAY HERE!!! FIGURE OUT HOW TO INTEGRATE BOARD DATA HERE
		BoardData boardData = new BoardData();
		
		BoardTile[][] boardTile = boardData.getBoardTiles();
		
		System.out.println("Each player must roll the dice. The player with the highest roll will go first!");
		for(int i=0; i<3; i++) {
			System.out.println(player[i].getName()+", roll the dice!");
			player[i].latestDiceRoll=rollDice();
			System.out.println("You rolled a "+ player[i].latestDiceRoll);
		}
		
		player=playerOrder(player);
		System.out.println("Here is the finalized order: ");
		for(int i=0; i<3; i++) {
			System.out.println(player[i].getName());
			player[i].latestDiceRoll=0;
		}
		
		int turn=1; int tileIndex = 0; int roll;
		int i = 0; int boardTileIndex=0; int roundsPassed = 0;
		
		//GAME BEGINS
		do {
			
			//Determining which player goes
			if(turn%3==1) i=0;
			else if(turn%3==2) i=1;
			else i=2;
			
			System.out.println(player[i]);
			//If the player is not in jail
			if(!player[i].inJail) {
				
				//Rolling dice
				roll=rollDice();
				System.out.println("You rolled a "+roll);
				
				//Moving on the board
				tileIndex=player[i].position+roll; 
				if(tileIndex>23) tileIndex=tileIndex-24; //Ensuring that player doesn't "go out" of the board
			
				//Updating player position
				BoardTile tileLanded= BoardTile.getTile(tileIndex, boardTile); //Finding the tile from the board data.
				player[i].updatePosition(tileLanded, roll);
				
				//If landed on a generic square (FREE PASS, GO, VISITING JAIL)
				if(tileLanded instanceof Generic) {
					Generic g= (Generic) tileLanded;
					System.out.println("You are on "+g.getName());
					g.updateSum(player[i]);
				}
				
				//If the tile landed upon is a property
				if(tileLanded instanceof Property) {
					
					//Temporary variable to store the property landed upon
					Property property = (Property) tileLanded; 
					
					 //If the property is not owned at all,
					if(property.getOwnershipStatus()==false) {
						System.out.println("You have landed on "+property.getName());
						
						if(player[i].isAffordable(property.getPrice())){
							player[i].propertyCollection.display();
							System.out.print("Do you want to buy this property?"
									+ "\nEnter YES to buy this property: ");
							String input= in.next();
							if(input.equalsIgnoreCase("yes")) {
								player[i].buyProperty();
								property.typeBenefits(player[i]);
							}
						}
						else System.out.println("SORRY, you can't afford this property!");
					}
					
					else{
						
						//If the player is the owner
						if(property.getOwner()==player[i]) {
							System.out.println("You have landed on your own property!");
							
							if(player[i].isAffordable(property.getHousePrice())) {
								
								System.out.print("Enter YES to add a house to "+property.getName());
								String input = in.next();
								if(input.equalsIgnoreCase("yes")) {
									player[i].upgradeProperty();
									property.addHouse();
								}
							}
							
							else System.out.println("SORRY, you can't afford any upgrades!");
						}
						
						//If another player is the owner
						else {
							System.out.println("You have landed on "+property.getOwner()+"'s "+property.getName()+"!\nYou must PAY RENT");
							player[i].payRent();
						}
					}	
				}
				
				//If you landed on a chance card.
				else if(tileLanded instanceof Chance) {
					Random rand = new Random();
					int code=in.nextInt(3)+1;
					Chance.performAction(code, player[i]);
					System.out.println(Chance.displayInstruction());
				}
				
				//You've landed on GOTOJAIL Tile
				else Jail.sendJail(player[i]);
				
			}
			
			//You are in jail
			else{
				System.out.println("Sorry, you can't play! You're still in jail!");
				player[i].roundsLeftJail--;
				if(player[i].roundsLeftJail==0) player[i].inJail=false;
			}
			
			++roundsPassed; //Increase # of turns
			++turn;//Move to next turn
		
		}while(player[i].getCash()>0);
		
		

		System.out.println("Game Terminated\nCalculating Winner.....");
		String winner;
		if(player[0].getWealth()>player[1].getWealth()) {
			if(player[0].getWealth()>player[2].getWealth()) {
				winner=player[0].getName();
			}
			else winner = player[2].getName();
		}
		else winner = player[1].getName();
		
		System.out.println("DRUMROLL.....");
		System.out.println(winner+" has WON the game!");
	
		
	}

}

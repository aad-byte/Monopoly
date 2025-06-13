package backend;

import java.util.Random;
import java.util.Scanner;

public class Player {
	static Scanner in = new Scanner(System.in);
	
	public int latestDiceRoll;
	public static int roundsPassed; //Keeping track of the # of rounds passed in the game.
	private final String name; //Name of player. Is encapsulated as it should not be changed through the course of the game
	
	public int position; //Integer to track the position of the player on the game board.
	public BoardTile location;
	public boolean inJail; //In the actual game, you can see if an another player is in jail.
	
	public double cash;//Cash owned by player should not be altered from the main program, but should be accessible by property and chance classes.
	private List propertyCollection; // List of all properties owned
	private double assetValue; //Double value to hold the sum of all property prices.
	public static boolean loserFound; //
	
	//Constructor
	Player(String n){
		//Constructor to instantiate player fields. Receives a name preferred by a player.
		name = n; //Sets name to the String received.
		cash=200; //Each player starts the game with $200.
		List propertyCollection = new List(); //Creating an empty list that would collect the properties the player would choose to buy
		position=0; //Position of the GO Tile
	}
	
	//Getter methods to access private fields
	public String getPlayerName() {
		return this.name;
	}
	public double getCash() {
		//Returns cash rounded to 2 decimal places.
		return Math.round(cash*100.0)/100.0;
	}
	public double getWealth() {
		//Returns total wealth rounded to 2 decimal places.
		return Math.round((cash+assetValue)*100.0)/100.0;
	}
	
	//Method to display basic player information
	public String toString() {
		String n= "Name: "+this.name;
		String c="Cash: $"+this.getCash();
		String p= "Properties Owned: "+ propertyCollection.owned;
		return n+"\n"+c+"\n"+p;
	}

	//FIGURE OUT HOW THIS IS IMPLEMENTED
	public void setPosition(BoardTile tile, int roll) { //needs casting
		this.location = tile;
		this.position= tile.position();
		latestDiceRoll=roll;
	}
	public void move(int move) {
		position+=move;
	}
	
	//Method to check if a player can buy a property
	public boolean canBuy(Property property) {
		//Receives a property
		//Returns true if player can afford property, else false
		return cash-property.getPrice()>0;
	}
	
	//Method allowing user to buy a property, when feasible based on player's cash
	public void buyProperty() {
		//Guides player to buy a property when they can afford it
		//Doesn't return anything
		
		//Temporary variable to store the property the player landed upon
		Property property = location.property;
		
		//Displaying property stats to help player decide whether to buy or not
		property.display();
		System.out.println("NOTE: If you buy this property, your balance will be $"+ (cash-property.getPrice()));
		
		System.out.print("Do you want to buy this property?\nEnter 'yes' to buy this property: "); //Prompting player to buy
		String choice=in.next(); //Getting player's preference.
		
		//----------------------------------------------------------------------------------------------------------------
		
		//Updating player and property fields based on choice
		if(choice.equalsIgnoreCase("yes")) {
			assetValue+=property.getPrice(); //adding property's price to assets owned
			cash-=property.getPrice(); //deducting cash
			propertyCollection.insert(property); //inserting property into list of properties owned by player
			System.out.println(property.getName()+" is added to your collection!");
		}
		else return;
	}

	//Method to calculate how many houses a player can build when they've landed on their property.
	public int housesPossible(Property property) {
		///Receives one of their properties
		//Calculates and returns possible # of houses based on cash
		//Returns -1 if the property is a train station
		
		//Checks if property is a train station/not
		if(property.isTrain) return -1;
		else return (int) (cash/property.getHousePrice());
	}
	
	//Method to upgrade property, when feasible based on player's cash
	public void upgradeProperty() {	
		//Method that guides player through upgradation process and updates fields accordingly
		//Doesn't return anything
		Property property = location.property;
		
		//Displaying property information
		property.display();
		
		//Letting user know how many houses they CAN build with the available cash.
		int houses = housesPossible(property);
		System.out.println("With the cash you have, you can build atmost "+houses+" houses and have a balance of $"+(cash-(houses*property.housePrice)));
		
		//Prompting for and getting # of houses the user wants built on the property.
		System.out.print("How many houses do you want to build? (Must be between 0 - "+houses+" inclusive): ");
		int wantsBuilt = in.nextInt();
		wantsBuilt = validateInput(wantsBuilt, 0, houses); //Ensuring the # of houses the player wants built is within the range specified.
		
		//------------------------------------------------------------------------------------------------------------------------------------------------
		
		//Updating property and player fields accordingly, if user wants upgrades to be done
		if(wantsBuilt!=0) {
			cash-=(wantsBuilt*property.housePrice); //Deducting cash from player's account
			property.rentValue+=wantsBuilt*property.rentIncreaseAmount; //Increasing rent value by set appreciation rate.
			property.noOfHouses+=wantsBuilt; //Increasing # of houses on the property.
		}
		else return;
	}
	
	//Method to deduct cash from a player when they land on other players' properties
	public void payRent() {
		//Method will be called only when player has ready cash to pay rent
		//Doesn't return anything
		
		Player other = location.property.getOwner(); //Determining recipient player who receives rent
		double amount = location.property.getPrice(); //Determining rent amount
		cash-=amount; //Decreasing cash from the player.
		other.cash+=amount; //Increasing the cash of the player on whose property the implicit player is on.
	}

	//For external classes like Chance to alter cash field
	public void editCash(double amount) {
		//Receives a money amount (could be positive or negative) that will be deducted or added to the player's cash
		cash+=amount;
	}
	
	public void sellProperty() {
		
	}
	
	
	//Static method in main
	private static int validateInput(int num, int min, int max) {
		if(min<=num && num<=max) return num;
		else {
			System.out.println("Invalid input! Number entered must be between "+min+" - "+max+" inclusive!");
			System.out.print("Enter again: ");
			num=in.nextInt();
		}
		return validateInput(num, min, max);
	}

}
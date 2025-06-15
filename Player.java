
public class Player {
	//Instance Fields
	public static final BoardTile[][] playersGameBoard= BoardData.getBoardTiles();
	
	private final String name; //String to store player name(Encapsulated as it should not be changed through the course of the game)
	public int position; //Integer to track the position of the player on the game board.
	public BoardTile location;
	public boolean inJail; //In the actual game, you can see if an another player is in jail.

	public int latestDiceRoll; //Integer that keeps track of all the latest dice roll of the player (from 1-6)
	public static int roundsPassed; // Integer to track of the # of rounds passed in the game. 
	
	public double cash;//Cash owned by player SHOULD NOT be altered from the main program, but should be accessible by property and chance classes.
	public List propertyCollection; // List of all properties owned
	private double assetValue; //Double value to hold the sum of all property prices.

	public int roundsLeftJail;
	public static boolean loserFound;
	
	//Actual constructor
	Player(String name){
		//Constructor to instantiate player fields. Receives a name preferred by a player.
		this.name = name; //Sets name to the String received.
		cash=200; //Each player starts the game with $200.
		propertyCollection=new List(); //Creating an empty list that would collect the properties the player would choose to buy
		position=0; //Position of the GO Tile
		latestDiceRoll=0;
	}
		
	//Getter Methods To Access Private Fields
	public String getName() {
		return this.name;
	}
	public double getCash() {
		//Returns cash rounded to 2 decimal places.
		return cash;
	}
	
	public double getWealth() {
		return cash+propertyCollection.getTotalAssetValue();
	}
	
	public List getList() {
		return propertyCollection;
	}
	
	//Method to display basic player information
	public String toString() {
		String n= "Name: "+this.name;
		String c="Cash: $"+this.getCash();
		String p= "Properties Owned: "+propertyCollection.owned;
		return n+"\n"+c+"\n"+p;
	}
	
	//Method to alter player cash
	public void editCash(double amount) {
		//Receives a money amount (could be positive or negative) that will be deducted or added to the player's cash
		//Allows external classes like Chance and the Main Program to alter cash of a player
		//Doesn't return anything
		cash+=amount;
	}
	
	//This method checks if a player can afford to upgrade or buy. 
	public boolean isAffordable(double amount) {
		if(cash>amount) return true;
		else return false;
	}
	
	//Method allowing player to buy a property, when feasible based on player's cash
	public void buyProperty() {
		Property property = (Property) location; //Temporary variable to store the property the player landed upon
		assetValue+=property.getPrice(); //adding property's price to assets owned
		cash-=property.getPrice(); //deducting cash
		propertyCollection.insert(property); //inserting property into list of properties owned by player
	}
	
	//Method to upgrade property, when feasible based on player's cash
	public void upgradeProperty() {	
		//Doesn't return anything
			Property property = (Property) location;//Temporary variable to store the property the player landed upon
			cash-=(property.getHousePrice()); //Deducting cash from player's account
	}
	
	//Method to deduct cash from a player when they land on other players' properties
	public void payRent() {
		//Method will be called only when player has ready cash to pay rent
		//Doesn't return anything
		Property property = (Property) location;
		Player other = ((Property) property).getOwner(); //Determining recipient player who receives rent
		double amount = property.rent; //Determining rent amount
		this.editCash(amount); //Decreasing cash from the player.
		other.editCash(amount); //Increasing the cash of the player on whose property the implicit player is on.
	}
	
	//Method to update position of the player
	public void updatePosition(BoardTile tile, int roll) {
		location = tile;
		position=tile.getPosition();
		latestDiceRoll=roll;
	}

}

/*
 * //Method to sell property 
	public void sellProperty(int pos) {
		Property sold = this.propertyCollection.delete(pos);  
		location.property.getOwner().propertyCollection.insert(sold);
	}
 */
//Method to check if a player can buy a property
	/*public boolean canBuy(Property property) {
		//Receives a property
		//Returns true if player can afford property, else false
		return cash-property.getPrice()>0;
	}
	//Method to calculate how many houses a player can build when they've landed on their property.
	public int housesPossible(Property property) {
		///Receives one of their properties
		//Calculates and returns possible # of houses based on cash
		//Returns -1 if the property is a train station
		
		//Checks if property is a train station/not
		if(property.isTrain) return -1;
		else return (int) (cash/property.getHousePrice());
	}*/
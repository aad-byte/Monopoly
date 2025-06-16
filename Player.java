
public class Player {
	//Instance Fields
	public static final BoardTile[][] playersGameBoard= BoardData.getBoardTiles();
	
	private final String name; //String to store player name(Encapsulated as it should not be changed through the course of the game)
	public int position; //Integer to track the position of the player on the game board.
	public BoardTile location; //Location of the player on the board
	public boolean inJail; //In the actual game, you can see if an another player is in jail.

	public int latestDiceRoll; //Integer that keeps track of all the latest dice roll of the player (from 1-6)
	
	private double cash;//Cash owned by player SHOULD NOT be altered from the main program, but should be accessible by property and chance classes.
	private List propertyCollection; // List of all properties owned
	private double assetValue; //Double value to hold the sum of all property prices.

	public int roundsLeftJail; 
	
	//Actual constructor
	Player(String name){
		//Constructor to instantiate player fields. Receives a name preferred by a player.
		this.name = name; //Sets name to the String received.
		cash=200; //Each player starts the game with $200.
		propertyCollection=new List(); //Creating an empty list that would collect the properties the player would choose to buy
		position=0; //Position of the GO Tile
		latestDiceRoll=0;
	}
		
	Player(BoardTile tile, String name){
		//Constructor to instantiate player fields. Receives a name preferred by a player.
		this.name = name; //Sets name to the String received.
		cash=500; //Each player starts the game with $1000.
		propertyCollection=new List(); //Creating an empty list that would collect the properties the player would choose to buy
		position=0; //Position of the GO Tile
		location = tile;
		//Added by Aadya
		roundsLeftJail = 0;
		inJail = false;
	}
	
	//Getter Methods To Access Private Fields
	public String getName() {
		return this.name;
	}
	public double getCash() {
		//Returns cash owned by the player
		return cash;
	}
	
	public double getWealth() {
		//Returns total wealth of the player
		return cash+propertyCollection.getTotalAssetValue();
	}
	
	public List getList() {
		//Returns the list of property collections
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
	
	//Method to update position of the player
	public void updatePosition(BoardTile tile, int roll, int latestDiceRoll2) {
		location = tile;
		position=tile.getPosition();
		latestDiceRoll=roll;
	}
		
	//This method checks if a player can afford to upgrade or buy. 
	public boolean isAffordable(double amount) {
		//Checks if the cash owned by player is greater than the amount that needs to be sent 
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
}
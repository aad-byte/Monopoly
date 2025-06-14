
public class Player {
	
	//Instance Fields
	private final String name; //String to store player name(Encapsulated as it should not be changed through the course of the game)
	public int position; //Integer to track the position of the player on the game board.
	public BoardTile location;
	public boolean inJail; //In the actual game, you can see if an another player is in jail.
	//added by Aadya
	public int roundsLeftJail;

	public int latestDiceRoll; //Integer that keeps track of all the latest dice roll of the player (from 1-6)
	public static int roundsPassed; // Integer to track of the # of rounds passed in the game. 
	
	
	public double cash;//Cash owned by player SHOULD NOT be altered from the main program, but should be accessible by property and chance classes.
	public List propertyCollection; // List of all properties owned
	private double assetValue; //Double value to hold the sum of all property prices.
	public static boolean loserFound; //
	
	//Constructor (CHANGE TO PARAMETERIZED CONSTRUCTOR)
	
	Player(BoardTile tile){
		//Constructor to instantiate player fields. Receives a name preferred by a player.
		name = "Player 1"; //Sets name to the String received.
		cash=1000; //Each player starts the game with $1000.
		propertyCollection=new List(); //Creating an empty list that would collect the properties the player would choose to buy
		position=0; //Position of the GO Tile
		location = tile;
		//Added by Aadya
		roundsLeftJail = 0;
		inJail = false;
	}
	
	Player(String name){
		//Constructor to instantiate player fields. Receives a name preferred by a player.
		this.name = name; //Sets name to the String received.
		cash=200; //Each player starts the game with $200.
		propertyCollection=new List(); //Creating an empty list that would collect the properties the player would choose to buy
		position=0; //Position of the GO Tile
	}
	
	//Method to update position of the player
	public void setPosition(BoardTile tile, int roll) {
		location = tile;
		position=tile.position;
		latestDiceRoll=roll;
	}
	
	//Getter Methods To Access Private Fields
	public String getName() {
		return this.name;
	}
	public double getCash() {
		//Returns cash rounded to 2 decimal places.
		//return Math.round(cash*100.0)/100.0; //Aadya - dont return cash by roudning, update cash in way such that it statys restricted to rtwo decimal places
		return cash;
	}
	public double getWealth() {
		//Returns total wealth rounded to 2 decimal places.
		return Math.round((cash+assetValue)*100.0)/100.0;
	}
	
	public void editCash(double amount) {
		//Receives a money amount (could be positive or negative) that will be deducted or added to the player's cash
		//Allows external classes like Chance and the Main Program to alter cash of a player
		//Doesn't return anything
		cash+=amount;
	}
		
	//Method to display basic player information
	public String toString() {
		String n= "Name: "+this.name;
		String c="Cash: $"+this.getCash();
		String p= "Properties Owned: "+propertyCollection.owned;
		return n+"\n"+c+"\n"+p;
	}
	
	//1. Method to check if a player can buy a property
	public boolean canBuy(Property property) {
		//Receives a property
		//Returns true if player can afford property, else false
		return cash-property.getPrice()>0;
	}
	
	//2. Method allowing player to buy a property, when feasible based on player's cash
	public void buyProperty() {
		
		//Temporary variable to store the property the player landed upon
		Property property = location.property;
		
		//Updating player and property fields based on choice
		assetValue+=property.getPrice(); //adding property's price to assets owned
		cash-=property.getPrice(); //deducting cash
		propertyCollection.insert(property); //inserting property into list of properties owned by player
	}

	//3. Method to calculate how many houses a player can build when they've landed on their property.
	public int housesPossible(Property property) {
		///Receives one of their properties
		//Calculates and returns possible # of houses based on cash
		//Returns -1 if the property is a train station
		
		//Checks if property is a train station/not
		if(property.isTrain) return -1;
		else return (int) (cash/property.getHousePrice());
	}
	
	//4. Method to upgrade property, when feasible based on player's cash
	public void upgradeProperty(int wantsBuilt) {	
		//Doesn't return anything
			
		//Updating property and player fields accordingly, if user wants upgrades to be done
		Property property = location.property;
		if(wantsBuilt!=0) {
			cash-=(wantsBuilt*property.housePrice); //Deducting cash from player's account
			this.assetValue+=property.rentIncreaseAmount; //CHECK FIELD
		}
		else return;
	}
	
	//5. Method to deduct cash from a player when they land on other players' properties
	public void payRent() {
		//Method will be called only when player has ready cash to pay rent
		//Doesn't return anything
		
		Player other = location.property.getOwner(); //Determining recipient player who receives rent
		double amount = location.property.rentValue; //Determining rent amount
		this.editCash(amount); //Decreasing cash from the player.
		other.editCash(amount); //Increasing the cash of the player on whose property the implicit player is on.
	}
	
	//7. Method to sell property 
	public void sellProperty(int pos) {
		Property sold = this.propertyCollection.delete(pos);
		location.property.getOwner().propertyCollection.insert(sold);
	}
	
	//8. Calculate Property Wealth
	public double returnWealth() {
		double wealth= cash+assetValue;
		return 
	}

	//added by Aadya 
	public List getList(){
		return propertyCollection; //return list of properties 
	}

}

/*public class Property extends BoardTile {
		private final String name = "";

		private Player owner;

		public double price;

		public double rentValue;

		boolean isOwned;

		int noOfHouses;

		int type;

		public boolean isTrain;

		public int rentIncreaseAmount;

		public int positionOnBoard;
		static double housePrice;

		Property(Player owner, int type, double price){
			isTrain=false;
			this.owner = owner;
			this.price = price;
			this.type =type;
			isOwned=true;
			positionOnBoard=3;
		}
		
		public String getName() {

		return name;

		}
		
		
		

		public static double getHousePrice() {

		return housePrice;

		}


		public double getPrice() {
			// TODO Auto-generated method stub
			return price;
		}


		public void display() {
			System.out.println("Type: "+type);
			System.out.println("Price: $"+ price);
			System.out.println("\n");
		}


		public Player getOwner() {
			// TODO Auto-generated method stub
			return null;
		}

}*/

public class Property extends BoardTile {
    private boolean owned;
    private Player owner;
    public double rent;
    private double assetValue;
    private final double price;
    private final int type;
    private int houses;
    private final String name;
    private static final double houseprice = 100; //cool idea to alter this after some certain number of rounds ot mimc houseing market 

    //constructor
    public Property(String name, int price, int position, int rent, int type){
        super(position); //initalize with boardTile (superclass) constructor
        this.name = name;
        this.price = price;
        this.assetValue = price;
        this.rent = rent;
        this.type = type; //add some logic so the only numbers accepted are between 0-4

        //addd value of the proerty fields realted to price and number of fields
        this.owner = null;
        this.owned = false;
        this.houses = 0;
    }

    public void updateOwner(Player player){
        this.owner = player;
        this.owned = true;
    }

    public void addHouse(){
      //  if(owned && (owner.getCash() > 50)){
            this.houses++;
         //   this.owner.editCash(-50);
            this.rent *= 2; //each house doubles the rent value
            this.assetValue += 20; //asset value increases with very house purchased
     //   }
    }

    public void typeBenefits(Player buyer){ //increases rent if the player owns proerpties of other types, for all the types of propeties owned (phrase better)
        //iterate through the player's prertie list, keeping track of each count 
        //everytime you come across a proeprty of hte same tpye, multiply it's rent by by 1.25
        //after iterating entire linked list, multiply current rent by 1.25to the power of the number of proeprties counted 

        int sameType = 0;
        Property[] collection = buyer.getList().toArray(); //get collection of properties in array format

        if(collection != null){
            for(int i = 0; i < collection.length; i++){
                //update rent by 1.5 for non-trainstation properties
                if(collection[i].getType() == this.type && this.type!=5){//compare each owned property's type to this property
                    collection[i].rent *= 1.5; //apply benifits to other properties of the same type owned by the player
                    sameType++;
                } else if(collection[i].getType() == this.type && this.type==5){ //update rent using base 2 for trainstation proerperties
                    collection[i].rent *= 2;
                    sameType++;
            }
        }

        //update this property's rent based on the number of other properties of the same type owned 
        if(this.type!=5){ //update by 1.5x if non-train station
        this.rent = Math.pow(1.5, sameType);
        } else if(this.type==5){ //update by 2x if train station
            this.rent = Math.pow(2, sameType);;
        }

    }

    }

    //acessor methods, upate with .this for better design
    public double getPrice(){
        return price;
    }

    public Player getOwner(){
        return owner;
    }

    public boolean getOwnershipStatus(){
        return owned;
    }

    public double getRent(){
        return rent;
    }

    public int getType(){
        return type;
    }

    public int getHouses(){
        return houses;
    }
    public String getName(){
        return name;
    }

    public double getHousePrice(){
        return houseprice;
    }

    public double getAssetValue() {
    	return assetValue;
    }
    
	public void display() {
		// TODO Auto-generated method stub
		
	}

    //do I need these or should I jst make rent public?
    /*public void editRentMultiplication(double x){
        this.rent *= x;
    }

    public void editRentAddition(double x){
        this.rent *= x;
    }*/
}

package backend;

public class Property extends BoardTile {
    private boolean owned; //status to determine if a property is owned
    private Player owner; //owner of property, direct object reference
    private double rent; //rent of property, only edited through instance methods
    private double assetValue; //assetvaluue of property, caluclation of property's worth
    private final double price; //fixed purchasing price
    private final int type; //to determine proeprties of same colour group
    private int houses; //number of house built on the property
    private final String name; //name of the property
    private static final double houseprice = 150; //for simplicty, the cost to purchase a house on any property is the same

    //constructor
    public Property(String name, int price, int position, int rent, int type, int x, int y){
        super(position, x, y); //initalize with boardTile (superclass) constructor
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

    // this method recives an object of type player, it assigns the player to the property, and updates the poerty to the owned status, it returns nothing
    public void updateOwner(Player player){
        this.owner = player; //update static field with object
        this.owned = true; //update status
    }

    //this method recives no parameters, it adds 1 house to the property and deducts cash and updates rent accordingly, it returns nothing
    public void addHouse(){
        if(owned && (owner.getCash() > 50)){
            this.houses++; //add house
            this.owner.editCash(-50); //deduct cash
            this.rent += 20; //each house adds 2.5 to the rent value
            this.assetValue += 5; //asset value increases with very house purchased
        }
    }

    //this method recives an object of type player.
    //it iterates through the player's properties, exponentialy increasing the rent for each property owned of the same type  as the the current property bought
    //this method returns nothing
    public void typeBenefits(Player buyer) {
    
        int sameType = 0; //counter to keep track of # of same types
        Property[] collection = buyer.getList().toArray(); // get collection of properties in array format
    
        if (collection != null) { // only contiune if array exists
            for (int i = 0; i < collection.length; i++) { //iterate through the aray
                if (collection[i] != null) { //  check if property is not null (saftey)

                    //determine the properties in the collection of the same type
                    if (collection[i].getType() == this.type && this.type != 5) {// update rent by 1.5x for non-train station properties
                        collection[i].rent *= 1.5; // apply benefits to other properties of the same type owned by the player
                        sameType++;
                    } 
                    else if (collection[i].getType() == this.type && this.type == 5) {  // update rent by 2x for train station properties
                        collection[i].rent *= 2;
                        sameType++;
                    }
                }
            }
    
            // update this property's rent based on the number of other same-type properties owned 
            if (this.type != 5) { // non-train station: scale by power of 1.5
                this.rent *= Math.pow(1.5, sameType);
            } else { // train station: scale by power of 2
                this.rent *= Math.pow(2, sameType);
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
    public Boolean getOwned(){
        return owned;
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

    public double getAssetValue(){
        return assetValue;
    }

}

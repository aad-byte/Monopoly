package backend;

public class Property extends BoardTile {
    private boolean owned;
    private Player owner;
    private double rent;
    private double assetValue;
    private final double price;
    private final int type;
    private int houses;
    private final String name;
    private static final double houseprice = 150; //cool idea to alter this after some certain number of rounds ot mimc houseing market 

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

    public void updateOwner(Player player){
        this.owner = player;
        this.owned = true;
    }

    public void addHouse(){
        if(owned && (owner.getCash() > 50)){
            this.houses++;
            this.owner.editCash(-50);
            this.rent += 20; //each house adds 2.5 to the rent value
            this.assetValue += 5; //asset value increases with very house purchased
        }
    }

    public void typeBenefits(Player buyer) {
        // increases rent if the player owns properties of the same type
        // iterate through the player's property list, keeping track of each count 
        // every time you come across a property of the same type, multiply its rent by 1.5 (or 2 for train stations)
        // after iterating through the entire list, multiply this property’s rent by 1.5^n (or 2^n), where n is number of same-type properties
    
        int sameType = 0;
        Property[] collection = buyer.getList().toArray(); // get collection of properties in array format
    
        if (collection != null) { // if array exists
            for (int i = 0; i < collection.length; i++) {
                if (collection[i] != null) { //  check if property is not null
                    // update rent by 1.5x for non-train station properties
                    if (collection[i].getType() == this.type && this.type != 5) {
                        collection[i].rent *= 1.5; // apply benefits to other properties of the same type owned by the player
                        sameType++;
                    } 
                    // update rent by 2x for train station properties
                    else if (collection[i].getType() == this.type && this.type == 5) {
                        collection[i].rent *= 2;
                        sameType++;
                    }
                }
            }
    
            // update this property's rent based on the number of other same-type properties owned
            if (this.type != 5) { // non-train station: scale by 1.5^sameType
                this.rent *= Math.pow(1.5, sameType);
            } else { // train station: scale by 2^sameType
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

    //do I need these or should I jst make rent public?
    /*public void editRentMultiplication(double x){
        this.rent *= x;
    }

    public void editRentAddition(double x){
        this.rent *= x;
    }*/
}

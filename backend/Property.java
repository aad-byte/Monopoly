package backend;

public class Property extends BoardTile {
    private boolean owned;
    private Player owner;
    public double rent;
    private final int price;
    private final int type;
    private int houses;
    private final String name;
    private static final int houseprice = 50; //cool idea to alter this after some certain number of rounds ot mimc houseing market 

    //constructor
    public Property(String name, int price, int position, int rent, int type){
        super(position); //initalize with boardTile (superclass) constructor
        this.name = name;
        this.price = price;
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
            this.rent *= 1.1;
        }
    }

    public void typeBenefits(Player buyer){
        //iterate through the player's prertie list, keeping track of each count 
        //everytime you come across a proeprty of hte same tpye, multiply it's rent by by 1.25
        //after iterating entire linked list, multiply current rent by 1.25to the power of the number of proeprties counted 

        int sameType = 0;
        Property[] collection = buyer.getList().toArray(); //get collection of properties in array format

        if(collection != null){
            for(int i = 0; i < collection.length; i++){
                if(collection[i].getType() == this.type){//compare each owned property's type to this property
                    collection[i].rent *= 1.25; //apply benifits to other properties of the same type owned by the player
                    sameType++;
                }
            }
        }

        //update this property's rent based on the number of other properties of the same type owned 
        this.rent = Math.pow(1.25, sameType);

    }

    //acessor methods, upate with .this for better design
    public int getPrice(){
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

    //do I need these or should I jst make rent public?
    /*public void editRentMultiplication(double x){
        this.rent *= x;
    }

    public void editRentAddition(double x){
        this.rent *= x;
    }*/
}

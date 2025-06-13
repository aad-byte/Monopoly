package backend;

public class Property extends BoardTile {
    private boolean owned;
    private Player owner;
    private int rent;
    private final int price;
    private final int type;
    private int houses;
    private final String name;
    private static final int houseprice = 50;

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
        if(owned && (Player.getCash() > 50)){
            this.houses++;
            this.owner.editCash(-50);
            this.rent *= 1.25;
        }
    }

    public void typeBenefits(boardCard[] masterCollection, Player buyer){
        //iterate through the player's prertie list, keeping track of each count 
        //everytime you come across a proeprty of hte same tpye, multiply it by 1.25
        //after iterating entire linked list, multiply current rent by 1.25to the power of the number of proeprties counted 

    }

    public int getPrice(){
        return price;
    }
}

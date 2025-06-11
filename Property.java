package backend;

public class Property {
    private boolean owned;
    private Player owner;
    private int rent;
    private final int price;
    private final int type;
    private int houses;
    private final int position;
    private final String name;
    private static final int houseprice = 50;

    public Property(String name, int price, int position, int rent, int type){
        this.name = name;
        this.price = price;
        this.position = position;
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

    public void updateRent(){
        //go to all other properites of the same type owned by the player and update the rent
    }
}

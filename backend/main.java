package backend;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
    
        Scanner input = new Scanner(System.in);
        //initalize the remiang arrays for chance card, jail cards, and a GO SQUARE (arry of length 1 exception)

        //sperate file intaltize all the names/ rent value sfor proerties to keep everything clean and then import here
        //instance method to return a refrence to a tile, based on it's given board number (position on the board)

       BoardData board = new BoardData(); //create board with respective properites and tiles

        //create 3 players
        Player[] unorderedPlayers = new Player[3];
        String name;
        for(int i = 0; i < unorderedPlayers.length; i++){
            System.out.println("Enter your name, Player " + (i+1));
            name = input.nextLine();
            unorderedPlayers[i] = new Player(BoardTile.getTile(0, board), name); //put in proper name 

        }

        Player[] orderedPlayers = orderPlayers(unorderedPlayers);

        for(int i = 0; i < orderedPlayers.length; i++){
        System.out.println(orderedPlayers[i].getName() + " rolled as final " + orderedPlayers[i].latestDiceRoll);
        }

        //finally lets start the game
        App game = new App();
        int roundNumber = 0; //dont know how to implement collect GO

        //game continues until 1 person runs of cash
        while((orderedPlayers[0].getCash() != 0 && orderedPlayers[1].getCash() != 0) && orderedPlayers[2].getCash() != 0){
            Player currentPlayer = orderedPlayers[roundNumber%3];
            System.out.println((orderedPlayers[roundNumber%3].getName()) + "'s turn");

            if (!currentPlayer.inJail){
                currentPlayer.latestDiceRoll = rollDice(); //player rolls dice
                int diceRoll = currentPlayer.latestDiceRoll;
                display(diceRoll);
                currentPlayer.position = (diceRoll + currentPlayer.position) % 24; //after tile 23, value will return to 0

                //get refrence to object on the board
                BoardTile currentTile = BoardTile.getTile(currentPlayer.position, board);

                //check which "type" of tile player has landed on 
                if(currentTile instanceof Property){
                    Property tile1 = (Property) currentTile; //cast tile to respective subclass
                    System.out.println("You landed on... " + tile1.getName() + "!");

                    if(!tile1.getOwned()){//if the property is unowned //shold prbly out
                        if(currentPlayer.canBuy(tile1)){//check if player can buy the proerty
                        System.out.println("Would you like to buy " + tile1.getName() + "?");
                        int answer = input.nextInt();
                        if(answer == 1){ //"1 is equivalent to yes for now"
                            tile1.typeBenefits(currentPlayer); //check for type benifts beofre appending to list
                            currentPlayer.buyProperty(tile1);
                            tile1.updateOwner(currentPlayer);

                        }
                        }else{
                            System.out.println("You dont have enough money to buy this property...");
                        }
                    }else if(tile1.getOwner() == currentPlayer){//if the property is owned by the player
                        if(currentPlayer.housesPossible(tile1)){
                            System.out.println("do you want to add a house?");
                            int response = input.nextInt();
                            if(response == 1){
                                tile1.addHouse(); //add house to property, update rent accroindingly
                                currentPlayer.addHouse(1, tile1); //subtract cash
                            }
                        }else{
                            System.out.println("it is not possible to intall a house");
                        }

                    }else{ //the tile is owned by someone else, must pay rent
                        System.out.println("The property is owned...you must pay rent: M " + tile1.getRent());
                        Player owner = tile1.getOwner(); //get the player who owns this preoperty
                        owner.editCash(tile1.getRent());
                        currentPlayer.editCash(-1*(tile1.getRent()));
                    }

                }else if (currentTile instanceof Chance){
                    System.out.println("You landed on a chance card!");
                    Chance tile2 = (Chance) currentTile; //cast tile to respective subclass

                    Random rand = new Random();
					int code=rand.nextInt(3)+1;
					Chance.performAction(code, currentPlayer, board);
                    Chance.displayInstruction();

                }else if (currentTile instanceof Generic){
                    System.err.println("Generic Square");
                    Generic tile3 = (Generic) currentTile; //cast tile to respective subclass
                    tile3.updateSum(currentPlayer);
                }
                else if (currentTile instanceof Jail){
                    System.err.println("OH NO! You are being sent to Jail...");
                    Jail tile4 = (Jail) currentTile; //cast tile to respective subclass
                    Jail.sendJail(currentPlayer); //send player to Jail
                }

            }else{
                System.out.println("You are in Jail, wait " + currentPlayer.roundsLeftJail + " more turns");
                currentPlayer.roundsLeftJail --;
                if(currentPlayer.roundsLeftJail == 0){
                    currentPlayer.inJail = false;
                }
            }
            roundNumber++;

        }

        System.out.println("Game Terminated\nCalculating Winner.....");
		String winner;
		if(orderedPlayers[0].getWealth()>orderedPlayers[1].getWealth()) {
			if(orderedPlayers[0].getWealth()>orderedPlayers[2].getWealth()) {
				winner=orderedPlayers[0].getName();
			}
			else winner = orderedPlayers[2].getName();
		}
		else winner = orderedPlayers[1].getName();
		
		System.out.println("DRUMROLL.....");
		System.out.println(winner+" has WON the game!");
        
    }

//instance methods 

//edit this to be SO MUCH MORE effecient
public static Player[] orderPlayers(Player[] unorderedPlayers) {
    // Step 1: Roll dice and display
    for (int i = 0; i < unorderedPlayers.length; i++) {
        unorderedPlayers[i].latestDiceRoll = rollDice();
        display(unorderedPlayers[i].latestDiceRoll);
    }

    // Step 2: Resolve ties until all rolls are unique
    boolean unique = false;
    while (!unique) {
        playerOrder(unorderedPlayers); // sort by highest roll

        // Only safe to check if we have 3 players (fixed-size logic)
        if (unorderedPlayers.length == 3) {
            int r0 = unorderedPlayers[0].latestDiceRoll;
            int r1 = unorderedPlayers[1].latestDiceRoll;
            int r2 = unorderedPlayers[2].latestDiceRoll;

            if (r0 != r1 && r1 != r2 && r0 != r2) {
                unique = true;
            } else {
                if (r0 == r1 && r1 == r2) {
                    for (int i = 0; i < 3; i++) {
                        unorderedPlayers[i].latestDiceRoll = rollDice();
                        display(unorderedPlayers[i].latestDiceRoll);
                    }
                } else if (r0 == r1) {
                    unorderedPlayers[0].latestDiceRoll = rollDice();
                    display(unorderedPlayers[0].latestDiceRoll);
                    unorderedPlayers[1].latestDiceRoll = rollDice();
                    display(unorderedPlayers[1].latestDiceRoll);
                } else if (r1 == r2) {
                    unorderedPlayers[1].latestDiceRoll = rollDice();
                    display(unorderedPlayers[1].latestDiceRoll);
                    unorderedPlayers[2].latestDiceRoll = rollDice();
                    display(unorderedPlayers[2].latestDiceRoll);
                }

                // Re-sort after re-rolling
                playerOrder(unorderedPlayers);
            }
        } else {
            unique = true; // Defensive: for future use with fewer players
        }
    }

    // Step 3: Build orderedPlayers array using addToEnd + deleteFromStart
    Player[] orderedPlayers = new Player[0];
    while (unorderedPlayers.length > 0) {
        orderedPlayers = addToEnd(unorderedPlayers[0], orderedPlayers);
        unorderedPlayers = deleteFromStart(1, unorderedPlayers);
    }

    return orderedPlayers;
}

//determine player order
public static Player[] playerOrder(Player[] list) {
    Player temp;
    for (int i = 0; i < list.length - 1; i++) {
        for (int j = 0; j < list.length - 1 - i; j++) {
            if (list[j].latestDiceRoll < list[j + 1].latestDiceRoll) {
                temp = list[j];
                list[j] = list[j + 1];
                list[j + 1] = temp;
            }
        }
    }
    return list;
}


//this method receives a list and and the number. It deletes the given number of nodes from the beginning of the list
public static Player[] deleteFromStart(int x, Player[] list) {
    Player[] temp = new Player[list.length - x];

    for (int i = 0; i < temp.length; i++) {
        temp[i] = list[i + x];
    }

    return temp;
}

public static Player[] addToEnd(Player player, Player[] list) {
    Player[] temp = new Player[list.length + 1];

    for (int i = 0; i < list.length; i++) {
        temp[i] = list[i];
    }

    temp[list.length] = player;

    return temp;
}
//roll dice
public static int rollDice(){
    Random rand = new Random();
    int numberRolled = rand.nextInt(6) + 1;
    return numberRolled;
}

public static void display(int dice){
    System.out.println("Dice rolled..."+dice);
}
}


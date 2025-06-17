package backend;


import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        App.main(args);
        Scanner input = new Scanner(System.in);
        //initalize the remiang arrays for chance card, jail cards, and a GO SQUARE (arry of length 1 exception)

        //sperate file intaltize all the names/ rent value sfor proerties to keep everything clean and then import here
        //instance method to return a refrence to a tile, based on it's given board number (position on the board)

       BoardData board = new BoardData(); //create board with respective properites and tiles

        //create 3 players
        Player[] unorderedPlayers = new Player[3];
        String name;
        for(int i = 0; i < unorderedPlayers.length; i++){
            App.gameLog.clearMessages();
            App.gameLog.appendMessage("Enter your name, Player " + (i+1) + " (in the terminal)");
            name = input.nextLine();
            unorderedPlayers[i] = new Player(BoardTile.getTile(0, board), name); //put in proper name 

        }

        Player[] orderedPlayers = orderPlayersInitial(unorderedPlayers);

    
        //finally lets start the game
        
        int roundNumber = 0; //dont know how to implement collect GO

        //game continues until 1 person runs of cash
        while((orderedPlayers[0].getCash() > 0 && orderedPlayers[1].getCash() > 0) && orderedPlayers[2].getCash() > 0){
            Player currentPlayer = orderedPlayers[roundNumber%3];
            App.gameLog.clearMessages();
            App.gameLog.appendMessage(orderedPlayers[roundNumber%3].getName() + "'s turn");


            //create a string of all properties owned by the player to dynamically update player info card
            Property[] owned = currentPlayer.getList().toArray();
            String propList;
            if(owned != null){
                propList = "";
                for(int i = 0; i < owned.length; i++){
                    propList += owned[i].getName();
                    if(i < owned.length - 1){
                        propList += ", ";
                    }
                }
            }else{
                propList = "No properties owned";
            }

            String avatar;
            if(roundNumber%3 == 0){
                avatar = "Car";
            }else if(roundNumber%3 == 1){
                avatar = "Hat";
            }else{
                avatar = "Boot";
            }

            App.playerDisplay.updateInfo(currentPlayer.getName(), currentPlayer.getCash(), propList, avatar); //update player info card on the screen

            if (!currentPlayer.inJail){
                currentPlayer.latestDiceRoll = rollDice(); //player rolls dice
                int diceRoll = currentPlayer.latestDiceRoll;

                //display dice
                App.dice.setDiceFace(diceRoll);
                //output in Game log
                App.gameLog.clearMessages();
                App.gameLog.appendMessage("You rolled..." + diceRoll);
                
                currentPlayer.position = (diceRoll + currentPlayer.position) % 24; //after tile 23, value will return to 0

                //get refrence to object on the board
                BoardTile currentTile = BoardTile.getTile(currentPlayer.position, board);

                //coordinates for Grid
                int xCoordinate = currentTile.getX();
                int yCoordinate = currentTile.getY();

                if(roundNumber%3 == 0){
                    App.monopolyGame.moveCar(xCoordinate, yCoordinate);
                }else if(roundNumber%3 == 1){
                    App.monopolyGame.moveHat(xCoordinate, yCoordinate);
                }else{
                    App.monopolyGame.moveBoot(xCoordinate, yCoordinate);
                }

                //check which "type" of tile player has landed on 
                if(currentTile instanceof Property){
                    Property tile1 = (Property) currentTile; //cast tile to respective subclass
                    App.gameLog.appendMessage("\nYou landed on... " + tile1.getName() + "!");


                    //update property info card

                    //check if owner exsists
                    String owner = "N/A";
                    if(tile1.getOwner() != null){
                        owner = tile1.getOwner().getName();
                    }

                    String houses = "N/A";
                    if(tile1.getType() != 5){
                        houses = Integer.toString(tile1.getHouses());
                    }

                    App.propertyDisplay.updateInfo(tile1.getName(), Double.toString(tile1.getPrice()), Double.toString(tile1.getRent()), owner, houses, Double.toString(tile1.getAssetValue()));
                

                    if(!tile1.getOwned()){//if the property is unowned //shold prbly out
                        if(currentPlayer.canBuy(tile1)){//check if player can buy the proerty
                            //prompt for purchase
                        
                        //prompt for purchase 
                        App.gameLog.appendMessage("\nWould you like to buy " + tile1.getName() + "? (If yes, enter 1 in the terminal, else enter any other key)");   
                        int answer = input.nextInt();

                        if(answer == 1){ //"1 is equivalent to yes for now"
                            tile1.typeBenefits(currentPlayer); //check for type benifts beofre appending to list
                            currentPlayer.buyProperty(tile1);
                            tile1.updateOwner(currentPlayer);

                        }
                        }else{
                            App.gameLog.appendMessage("\nYou dont have enough money to buy this property...");
                            App.gameLog.appendMessage("\nEnter any key to continue");
                            String ans = input.next();
                        }
                    }else if(tile1.getOwner() == currentPlayer){//if the property is owned by the player
                        App.gameLog.appendMessage("\nYou own this house!");
                        if(currentPlayer.housesPossible(tile1)){

                            //prompt to purchase a house
                            App.gameLog.appendMessage("\ndo you want to add a house? (If yes, enter 1 in the terminal, else enter any other key)");

                            
                            int response = input.nextInt();
                            if(response == 1){
                                tile1.addHouse(); //add house to property, update rent accroindingly
                                currentPlayer.addHouse(1, tile1); //subtract cash
                            }
                        }else{
                            App.gameLog.appendMessage("\nit is not possible to intall a house");
                            App.gameLog.appendMessage("\nEnter any key to continue");
                            String ans = input.next();
                        }

                    }else{ //the tile is owned by someone else, must pay rent
                        App.gameLog.appendMessage("\nThe property is owned...you must pay rent: M " + tile1.getRent());
                        
                        Player Owner = tile1.getOwner(); //get the player who owns this preoperty
                        Owner.editCash(tile1.getRent());
                        currentPlayer.editCash(-1*(tile1.getRent()));

                        App.gameLog.appendMessage("\nEnter any key to continue");
                            String ans = input.next();
                    }

                }else if (currentTile instanceof Chance){
                    App.gameLog.appendMessage("\nYou landed on a chance card!");

                    //Update info card
                    String na = "N/A";
                    App.propertyDisplay.updateInfo("Chance Card", na, na, na, na, na);
                    
                    Chance tile2 = (Chance) currentTile; //cast tile to respective subclass

                    Random rand = new Random();
					int code=rand.nextInt(3)+1;
					Chance.performAction(code, currentPlayer, board);

                    //if chance card moved the player in jail, reflet this on the board
                    if(currentPlayer.inJail){
                        if(roundNumber%3 == 0){
                            App.monopolyGame.moveCar(78, 78);
                        }else if(roundNumber%3 == 1){
                            App.monopolyGame.moveHat(78, 78);
                        }else{
                            App.monopolyGame.moveBoot(78, 78);
                        }
                    }

                    //if chance card caused movement, have to display it on the board
                    if(Chance.isMovement){
                         //get tile to move player
                        BoardTile tempTile = BoardTile.getTile(currentPlayer.position, board);

                        //determine  x and y coordinates for new tile
                        int gridX = tempTile.getX();
                        int gridY = tempTile.getY();

                        if(roundNumber%3 == 0){ //move respective avatar to grid cooridnates
                            App.monopolyGame.moveCar(gridX, gridX);
                        }else if(roundNumber%3 == 1){
                            App.monopolyGame.moveHat(gridX, gridY);
                        }else{
                            App.monopolyGame.moveBoot(gridX, gridY);
                        }
                    }

                    //Append Chance card instructions to the game log
                    App.gameLog.appendMessage(Chance.retrunInstruction());
                    App.gameLog.appendMessage("\nEnter any key to continue");
                            String ans = input.next();

                }else if (currentTile instanceof Generic){
                    
                    Generic tile3 = (Generic) currentTile; //cast tile to respective subclass

                    //Update info card
                    String na = "N/A";
                    App.propertyDisplay.updateInfo(tile3.getName(), na, na, na, na, na);
                    
                    App.gameLog.appendMessage("\nNothing happens, you landed on " + tile3.getName());
                    App.gameLog.appendMessage("\nEnter any key to continue");
                            String ans = input.next();
                }
                else if (currentTile instanceof Jail){
                    App.gameLog.appendMessage("\nOH NO! You are being sent to Jail...");

                    //Update info card
                    String na = "N/A";
                    App.propertyDisplay.updateInfo("GO TO JAIL", na, na, na, na, na);
                    
                    Jail tile4 = (Jail) currentTile; //cast tile to respective subclass
                    Jail.sendJail(currentPlayer); //send player to Jail

                    //send player to jail visually on the board
                    if(roundNumber%3 == 0){
                        App.monopolyGame.moveCar(78, 78);
                    }else if(roundNumber%3 == 1){
                        App.monopolyGame.moveHat(78, 78);
                    }else{
                        App.monopolyGame.moveBoot(78, 78);
                    }

                    App.gameLog.appendMessage("\nEnter any key to continue");
                            String ans = input.next();

                }

            }else{
                App.gameLog.clearMessages();
                App.gameLog.appendMessage("You are in Jail, wait " + currentPlayer.roundsLeftJail + " more turns");
                
                currentPlayer.roundsLeftJail --;
                if(currentPlayer.roundsLeftJail == 0){
                    currentPlayer.inJail = false;
                }
                App.gameLog.appendMessage("\nEnter any key to continue");
                            String ans = input.next();
            }

            int continueConfirm ;
          /*   do{
                App.gameLog.appendMessage("Please input 1 in the terminal to continue...");
                continueConfirm = input.nextInt();
            }while(continueConfirm != 1); */
            
            roundNumber++;

        }

        App.gameLog.appendMessage("Game Terminated\nCalculating Winner.....Check the terminal");
        
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

// methods 



//this method receives an array of unodered players
//it prompts players for dice rolls to determine the order of turns in the game with multi level sorting
//the method retruns an array of ordered players
public static Player[] orderPlayersInitial(Player[] unorderedPlayers) {
    Player[] orderedPlayers = new Player[0]; //decalre new array of ordered players to append to 

    Scanner input = new Scanner(System.in);
    App.gameLog.clearMessages();
    App.gameLog.appendMessage("Lets organize players based on dice rolls...");
    App.gameLog.appendMessage("Enter any key to continue...");
    String ans = input.next();
    // Step 1: Roll dice and display
    for (int i = 0; i < unorderedPlayers.length; i++) {
        unorderedPlayers[i].latestDiceRoll = rollDice();
        int diceRoll = unorderedPlayers[i].latestDiceRoll;
        App.gameLog.clearMessages();
        App.gameLog.appendMessage(unorderedPlayers[i].getName() + " rolled " + diceRoll);
        App.dice.setDiceFace(diceRoll);
        App.gameLog.appendMessage("Enter any key to continue...");
        ans = input.next();
    }

    // Step 2: Resolve ties until all rolls are unique
    boolean unique = false;
    int diceRoll;
    while (!unique) { //keep repeating until all players are a part of ordered player list
        playerOrderDiceRoll(unorderedPlayers); // sort by highest roll, do I need to equat array to function or does it already assign it?

        if (unorderedPlayers.length == 3) { //saftey check if player list every changes 
            //asign alias to each player's dice roll
            int r0 = unorderedPlayers[0].latestDiceRoll;
            int r1 = unorderedPlayers[1].latestDiceRoll;
            int r2 = unorderedPlayers[2].latestDiceRoll;

            if (r0 != r1 && r1 != r2 && r0 != r2) { //if all rolls were different on the first iteration, then unqie order of players can be detmierined
                unique = true; //it wille exit loop

                //imdetaley assing the orderplayers array as the sorted by dice rolls version of unorderd players
                orderedPlayers = playerOrderDiceRoll(unorderedPlayers); 
            } else {
                if (r0 == r1 && r1 == r2) { //if all three are equal , do not lift bool flag, so the loop continues

                    App.gameLog.clearMessages();
                    App.gameLog.appendMessage("All the rolls were the same, each player must re-roll");
        
                    for (int i = 0; i < 3; i++) {//roll dice for all three again, the loop will repeat
                        unorderedPlayers[i].latestDiceRoll = rollDice();
                        diceRoll = unorderedPlayers[i].latestDiceRoll;
                        App.gameLog.clearMessages();
                        App.gameLog.appendMessage(unorderedPlayers[i].getName() + " rolled " + diceRoll);
                        App.dice.setDiceFace(diceRoll);
                        App.gameLog.appendMessage("Enter any key to continue...");
                        ans = input.next();
                    }
                } else if (r0 == r1) {//if the first two are equal

                    orderedPlayers = addToStart(unorderedPlayers[2], orderedPlayers); //append sorted last player
                    unorderedPlayers = deleteFromEnd(unorderedPlayers); //remove player from end of

                    while(!unique){ //repeat until two ditinct values are rolled
                        App.gameLog.clearMessages();
                        App.gameLog.appendMessage(unorderedPlayers[0].getName() + " and " + unorderedPlayers[1]. getName() + " must re-roll"); // output which players need to re oll
                        App.gameLog.appendMessage("Enter any key to continue...");
                        ans = input.next();

                        unorderedPlayers[0].latestDiceRoll = rollDice(); //roll dice for 1 player
                        diceRoll = unorderedPlayers[0].latestDiceRoll;

                        //show dice output in GUI
                        App.gameLog.clearMessages();
                        App.gameLog.appendMessage(unorderedPlayers[0].getName() + " rolled " + diceRoll);
                        App.dice.setDiceFace(diceRoll);

                        App.gameLog.appendMessage("Enter any key to continue...");
                        ans = input.next();

                        //roll dice for 2 player
                        unorderedPlayers[1].latestDiceRoll = rollDice();
                        diceRoll = unorderedPlayers[1].latestDiceRoll;

                        //output dice reults
                        App.gameLog.clearMessages();
                        App.gameLog.appendMessage(unorderedPlayers[1].getName() + " rolled " + diceRoll);
                        App.dice.setDiceFace(diceRoll);

                        App.gameLog.appendMessage("Enter any key to continue...");
                        ans = input.next();

                        if(unorderedPlayers[0].latestDiceRoll != unorderedPlayers[1].latestDiceRoll){
                            unique = true; //if their dice out comes are uniqe, they can be sorted, thus both loos can be broken
                        }

                    }
                    //exit loop and order players
                    unorderedPlayers = playerOrderDiceRoll(unorderedPlayers);
                    //append to ordered players list and delete from other list
                    orderedPlayers = addToStart(unorderedPlayers[1], orderedPlayers);
                    unorderedPlayers = deleteFromEnd(unorderedPlayers);
                    orderedPlayers = addToStart(unorderedPlayers[0], orderedPlayers);

                } else if (r1 == r2) { //if last two are equal

                    orderedPlayers = addToEnd(unorderedPlayers[0], orderedPlayers); //append sorted 1st player
                    unorderedPlayers = deleteFromStart(unorderedPlayers); //remove player from start

                    while(!unique){ //repeat until two ditinct values are rolled
                        App.gameLog.clearMessages();
                        App.gameLog.appendMessage(unorderedPlayers[0].getName() + " and " + unorderedPlayers[1]. getName() + " must re-roll"); // output which players need to re oll
                        App.gameLog.appendMessage("Enter any key to continue...");
                        ans = input.next();

                        unorderedPlayers[0].latestDiceRoll = rollDice(); //roll dice for 1 player
                        diceRoll = unorderedPlayers[0].latestDiceRoll;

                        //show dice output in GUI
                        App.gameLog.clearMessages();
                        App.gameLog.appendMessage(unorderedPlayers[0].getName() + " rolled " + diceRoll);
                        App.dice.setDiceFace(diceRoll);

                        App.gameLog.appendMessage("Enter any key to continue...");
                        ans = input.next();

                        //roll dice for 2 player
                        unorderedPlayers[1].latestDiceRoll = rollDice();
                        diceRoll = unorderedPlayers[1].latestDiceRoll;

                        //output dice reults
                        App.gameLog.clearMessages();
                        App.gameLog.appendMessage(unorderedPlayers[1].getName() + " rolled " + diceRoll);
                        App.dice.setDiceFace(diceRoll);

                        App.gameLog.appendMessage("Enter any key to continue...");
                        ans = input.next();

                        if(unorderedPlayers[0].latestDiceRoll != unorderedPlayers[1].latestDiceRoll){
                            unique = true; //if their dice out comes are uniqe, they can be sorted, thus both loos can be broken
                        }

                    }
                    //exit loop and order players
                    unorderedPlayers = playerOrderDiceRoll(unorderedPlayers);
                    //append to ordered players list and delete from other list
                    orderedPlayers = addToEnd(unorderedPlayers[0], orderedPlayers);
                    unorderedPlayers = deleteFromStart(unorderedPlayers);
                    orderedPlayers = addToEnd(unorderedPlayers[0], orderedPlayers);
                }

                // re-sort after re-rolling - for unqie case of 3 
                playerOrderDiceRoll(unorderedPlayers);
            }
        } 
    }

    
    App.gameLog.clearMessages();
    App.gameLog.appendMessage("Order of Players:");

    //iterate thorugh orderd player's list to display final order for players
    System.out.println(orderedPlayers.length + " = orderplayers length");
    for(int i = 0; i < orderedPlayers.length; i++){
        App.gameLog.appendMessage("\n"+ (i+1) + ". " + orderedPlayers[i].getName());
    }

    App.gameLog.appendMessage("Enter any key to continue...");
    ans = input.next();
    return orderedPlayers;
}

// this method recives an array of players
//it sorts the players in descending order based on the value of their latest dice roll
//the method returns the ordered array
public static Player[] playerOrderDiceRoll(Player[] list) {
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


//this method receives a list and and the number. It deletes the given number of nodes from the beginning of the list, create one for delete from end and adddtoStart
public static Player[] deleteFromStart(Player[] list) {
    Player[] temp = new Player[list.length - 1];

    for (int i = 0; i < temp.length; i++) {
        temp[i] = list[i + 1];
    }

    return temp;
}

public static Player[] deleteFromEnd(Player[] list) {
    Player[] temp = new Player[list.length - 1];

    for (int i = 0; i < temp.length; i++) {
        temp[i] = list[i];
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

public static Player[] addToStart(Player player, Player[] list) {
    Player[] temp = new Player[list.length + 1];

    for (int i = 0; i < list.length; i++) {
        temp[i+1] = list[i];
    }

    temp[0] = player;

    return temp;
}
//this method recives no parameters
//it simulates a dice roll by generating a random number from 1-6
//it returns the number generated
public static int rollDice(){
    Random rand = new Random(); //random var
    int numberRolled = rand.nextInt(6) + 1; //generate random num
    return numberRolled;//return random num
}

//fix up player sorting and then get rid of this
public static void display(int dice){
    System.out.println("Dice rolled..."+dice);
}
}


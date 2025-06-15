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
    
    BoardTile temp =  getTile(4, board);

    //must do this
    if(temp instanceof Property){
        Property p = (Property)temp;
    }
    else if(temp instanceof Generic){
        Generic g = (Generic)temp;
    }else if (temp instanceof Chance){
        Chance c = (Chance)temp;
    }else if(temp instanceof Jail){
        Jail j = (Jail)temp;
    }

    //create 3 players
    Player[] unorderedPlayers = new Player[3];
    for(int i = 0; i < unorderedPlayers.length; i++){
        unorderedPlayers[i] = new Player(getTile(0, board), i); //put in proper name 
    }



    
}

//instance methods 

//method to retrive board class object reference based on position on board

public static BoardTile getTile(int position, BoardData board){
    BoardTile tile = null;
    for(int i = 0; i < board.tiles.length; i++){
        for(int j = 0; j < board.tiles[i].length; j++){
            if(board.tiles[i][j].getPosition() == position){
                tile = board.tiles[i][j];
            }
        }
    }

    return tile;
}

public static Player[] orderPlayers(Player[] unorderedPlayers){
    for(int i = 0; i < unorderedPlayers.length; i++){
        unorderedPlayers[i].latestDiceRoll = rollDice();
        display(unorderedPlayers[i].latestDiceRoll);
    }

    //order the players
    playerOrder(unorderedPlayers);

    //create array to store ordered players
    Player[] orderedPlayers = new Player[0];

    while(unorderedPlayers.length != 0){
        if(unorderedPlayers[0] != unorderedPlayers[1]){ //if the first shares nothing common with the second
            addToEnd(unorderedPlayers[0], orderedPlayers);
            deleteFromStart(1, orderedPlayers);
            if(unorderedPlayers[0] != unorderedPlayers[1]){
                addToEnd(unorderedPlayers[0], orderedPlayers);
                deleteFromStart(1, orderedPlayers);
            }else{
                int roll1, roll2;
                do{
                    roll1 = rollDice();
                    unorderedPlayers[0].latestDiceRoll = roll1;
                    display(roll1);
                    roll2 = rollDice();
                    unorderedPlayers[1].latestDiceRoll = roll2;
                    display(roll2);
                }while(roll1 == roll2);
            }
            playerOrder(unorderedPlayers);
            for(int i = 0; i < 2; i++){//add players with distinct dice rolls and their respcitce orderes to list of ordered players
                addToEnd(unorderedPlayers[0], orderedPlayers);
                deleteFromStart(1, orderedPlayers);
            }
        }else if(unorderedPlayers[0].latestDiceRoll == unorderedPlayers[1].latestDiceRoll && unorderedPlayers[1].latestDiceRoll != unorderedPlayers[2].latestDiceRoll){
            
        }
        
    }
}

//determine player order
public static Player[] playerOrder(Player[] list){
    int unsortedLength = list.length -1;
    Player temp;
    for(int i = unsortedLength; i > 0; i++){
        for(int j = 0; j < unsortedLength; j++){
            if(list[i].latestDiceRoll < list[i+1].latestDiceRoll ){
                temp = list[i];
                list[i] = list[i+1];
                list[i+1] = temp;
            }
        }
        unsortedLength -= 1;
    }
    return list;
}

//this method receives a list and and the number. It deletes the given number of nodes from the beginning of the list
public static Player[] deleteFromStart(int x, Player[] list){
    Player[] temp = new Player[list.length - x];

    for(int i = 0; i < list.length - 2; i++){
        temp[i] = list[i+x];
    }

    return temp;
}

public static Player[] addToEnd(Player player, Player[] list){
    Player[] temp = new Player[list.length + 1];

    //copy all data from list to temp
    for(int i = 0; i < list.length + 1; i++){
        temp[i] = list[i];
    }

    //append new player
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


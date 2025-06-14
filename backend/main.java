package backend;
public class main {
    public static void main(){
    

        //initalize the remiang arrays for chance card, jail cards, and a GO SQUARE (arry of length 1 exception)

        //sperate file intaltize all the names/ rent value sfor proerties to keep everything clean and then import here
        //instance method to return a refrence to a tile, based on it's given board number (position on the board)

        /*
            Create board class;
            First create board tiles
            Create three players 
            First we would need dice rolls from each player, determine their order start the game

            Array holds references to player;
            Variable to keep track of turns, mod 3 = 1, player’s 1’st prompt, mod3 = 2 player 2’s prompt and mod 3 = 0 = player 3’s prompt

            Do while loop to iterate through each player, condition to terminate with ORs and player’s cash values (even if one player loses cash → game terminates)

            First set player to round turn 
            If they are not in jail
            Roll the dice, update player’s instance to to hold reference to board tile (method in main)
            If player lands on a property…
            Update instance filed to position
            If the property is unowned && its affordable 
            Ask if they want to buy it
            Add to list of properties (Player field)
            Update Property to owned (Property Field)
            Update Properties owner to Player obj (Property Field)
            Run type Benefits (Property method)
            If the property is owned by someone else
            Grab the properties rent, and add it the property owner’s cash value
            Grab the properties rent and deduct from the current player
            If the owned by the player && (property is NOT a train station && house is affordable)
            If the player wants to add houses
            Use add house method 
            If player lands on Chance
            Position turns null
            If they land on jail (GO TO JAIL TILE)


            If player is on Generic square (Includes GO, FREE PASS, JUST VISITING JAIL)
            Update collectSum and add to player’s cash value 
            If they are in jail output:
            Subtract 1 from the rounds
            If the value is 0, then change the value to boolean
            Your still in jail…
            End of each turn, add 1 to the round turn
            Aftermath + Determine Winner:
            For each player calculate property wealth and cash wealth to determine total wealth
            Crown the highest player the winner!


            Jai

            Functionality of jail board tile
            It sets position to jail square
            It sets in jail TRUE
            It sets rounds remaining in jail to 2

         */
    }
}

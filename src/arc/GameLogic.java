package arc;

import obj.*;

public class GameLogic {
    // The static functions of the game logic that act upon the objects

    // The variables needed by the game logic
    private static GameState state;
    private static Round round;

    // Method to initialize the game state
    public static void initializeGame(){state = new GameState();}

    // Method to start the round by creating new round object
    public static void startRound(){round = new Round();}

    // Method that occurs at the end of each round to print gamestate and ask player if continue
    public static void endRound(){
        System.out.println(state);
        // Then will ask if player wants to continue, which keeps status set to true. for now, sets to false and ends loop.
        state.setGameStatus(false);
    }

    // Method to sum up players hands
    public static int sumPlayerHand(Player player){
        // Temp values to handle all face cards being 10 and ace handling
        int tempValue1;
        int tempValue2;
        int tempValue3;

        // Evaluate card 1
        if(player.getCard1().getValue() > 10){tempValue1 = 10;}
        else{tempValue1 = player.getCard1().getValue();}

        // Evaluate card 2
        if(player.getCard2().getValue() > 10){tempValue2 = 10;}
        else{tempValue2 = player.getCard1().getValue();}

        // Evaluate card 3 with standing in mind (when it is null)
        if(player.getCard3() != null && player.getCard3().getValue() > 10){
            tempValue3 = 10;
        } else if(player.getCard3() == null){
            tempValue3 = 0;
        } else{tempValue3 = player.getCard1().getValue();}

        // Sum the values and return it
        int sum = tempValue1 + tempValue2 + tempValue3;
        return sum;
    }

    // Method to deal the cards to the players in the round
    public static void dealCards(){
        // The first card is dealt to the player's first slot
        round.dealCard(round.getPlayer1(),1);
        // The second card is dealt to the house's first slot
        round.dealCard(round.getPlayer2(),1);
        // The third card is dealt to the player's second slot
        round.dealCard(round.getPlayer1(),2);
        // The fourth card is dealt to the house's second slot
        round.dealCard(round.getPlayer2(),2);

        // Have the system read out what cards the player and dealer has
        System.out.println("You have " + round.getPlayer1().getCard1() + "and " + round.getPlayer1().getCard2());
        System.out.println("House has " + round.getPlayer2().getCard1());
    }

    // Method to ask if the player wishes to hit or stand
    public static void askHitOrStand(){Utilities.hitOrNo(Utilities.askPlayerInput("Hit or Stand?"),round);}

    // Method for playing after the player decides to hit or stand
    public static void playAfterHitOrStand(){
        // If hit, add extra card to them
        if(round.getHitStatus()){
            round.dealCard(round.getPlayer1(),3);
        } else if(!round.getHitStatus()){} // If they stand, do nothing and move on
        else{ //If something else, break with error code
            System.out.println("Fatal error! Hit status unexpected value.");
            System.exit(1);
        }
    }

    // Method to check who has won!
    public static void checkWin(){
        // Sum and store the players' hands
        int p1Sum = sumPlayerHand(round.getPlayer1());
        System.out.println("Player has " + p1Sum);
        int p2Sum = sumPlayerHand(round.getPlayer2());
        System.out.println("House has " + p2Sum);

        // Win and loss conditions
        if(p1Sum > 21 && p2Sum <= 21){
            System.out.println("Player is bust! \n Player loses.");
            state.setLosses(state.getLosses() + 1);
        } else if(p1Sum == 21 && p2Sum != 21){
            System.out.println("Player has Blackjack! \n Player wins.");
            state.setWins(state.getWins() + 1);
        } else if((p1Sum == 21 && p2Sum == 21) || (p1Sum > 21 && p2Sum > 21) || ((p1Sum == p2Sum) && p1Sum < 21)){
            System.out.println("Tie! \n Nobody wins.");
            state.setTies(state.getTies() + 1);
        } else if(p1Sum < 21 && p2Sum > p1Sum){
            System.out.println("Player loses!");
            state.setLosses(state.getLosses() + 1);
        } else if(p1Sum < 21 && p2Sum < p1Sum){
            System.out.println("Player wins!");
            state.setWins(state.getWins() + 1);
        }
    }

    // The main game loop that uses all the above functions
    public static void playGameLoop(){
        initializeGame();
        while(state.getGameStatus()){
            startRound();
            dealCards();
            askHitOrStand();
            playAfterHitOrStand();
            checkWin();
            endRound();
        }
    }
}

package arc;

import obj.*;

public class GameLogic {
    // The static functions of the game logic that act upon the objects

    // Update: should iterate through every card every time one is added to see if an ace is added to account for third or fourth position aces
    // Seemingly after player busts, the logic continues on and needs to end the round or the game to stop that

    // The variables needed by the game logic
    private static GameState state;
    private static Round round;

    // Method to initialize the game state
    public static void initializeGame(){state = new GameState();}

    public static void endGame(){state.setGameStatus(false);}
    public static void continueGame(){state.setGameStatus(true);}

    // Method to start the round by creating new round object
    public static void startRound(){
        if(!state.getRoundStatus()){
            state.setRoundStatus(true);
            state.setHandCounter(state.getHandCounter()+1);
            System.out.println("Round: " + state.getHandCounter());
            round = new Round();
        }
    }

    // Method that occurs at the end of each round to print gamestate and ask player if continue
    public static void endRound(){
        //Debug Lines
        //System.out.println("Hit debug for end round.");
        //System.out.println("EndRound first values: " + state.getGameStatus() + " | " + state.getRoundStatus());

        System.out.println(state);
        state.setRoundStatus(false);

        //Debug
        //System.out.println("EndRound second values: " + state.getGameStatus() + " | " + state.getRoundStatus());

        Utilities.askQuestion(round,Question.CONTINUEORNOT);

        //Debug
        //System.out.println("EndRound third values: " + state.getGameStatus() + " | " + state.getRoundStatus());
    }

    // Method to sum up players hands with the included architecture for if ace is 1 or 11
    public static void sumPlayerHand(Player player){
        int sum = 0;
        // If ace is 1
        if(!player.getAceStatus()) {
            for(Card card : player.getPlayerHand().getCards()){sum += card.getCardRank().getRankValue();}
        }
        // If ace is 11
        else{
            for(Card card : player.getPlayerHand().getCards()){
                // Sums up every card, ace being 1
                sum += card.getCardRank().getRankValue();
                // If the card is an ace, then it adds 10 to the sum (1 + 10 for 11)
                if(card.getCardRank().equals(CardRank.ACE)){sum += 10;}
            }
        }
        player.setHandSum(sum);
    }

    // Method to check if player busts
    public static void checkBustOrBlackjack(Player player){
        // Check if player busts
        if(player.getHandSum() > 21){
            player.setBusted(true);
        }
        // Check if player has blackjack
        else if(player.getHandSum() == 21){
            player.setBlackjack(true);
        }
    }

    // Method to actually run the bust or blackjack code -- hardcoded just to player, need to swap that around
    public static void runPlayerBustOrBlackjack(Player player){
        checkBustOrBlackjack(player);
        if(player.getBusted()){
            System.out.println("Player busts! \n Player loses.");
            state.setLosses(state.getLosses()+1);
            endRound();
        } else if(player.getBlackJack()){
            System.out.println("Player has blackjack! \n Player wins.");
            state.setWins(state.getWins()+1);
            endRound();
        }
    }

    // Method to run blackjack or bust for when house has it -- hardcoded, can make more general
    public static void runHouseBustOrBlackjack(Player house){
        checkBustOrBlackjack(house);
        if(house.getBusted()){
            System.out.println("House busts! \n Player wins.");
            state.setWins(state.getWins()+1);
            endRound();
        } else if(house.getBlackJack()){
            System.out.println("House has blackjack! \n House wins.");
            state.setLosses(state.getLosses()+1);
            endRound();
        }
    }

    // Method to deal the cards to the players in the round
    public static void dealCards(){
        // The first card is dealt to the player's first slot and checked if ace, asks if so
        round.dealCard(round.getPlayer1());
        System.out.println("Player gets " + round.getPlayer1().getCard(0));
        if(checkPlayerAce(round.getPlayer1().getCard(0))){Utilities.askQuestion(round,Question.ONEORELEVEN);}

        // The second card is dealt to the house's hand - ace checking goes after they get both cards
        round.dealCard(round.getPlayer2());

        // The third card is dealt to the player's hand and checked if ace, if first card is not ace
        round.dealCard(round.getPlayer1());
        System.out.println("Player gets " + round.getPlayer1().getCard(1));
        if(!checkPlayerAce(round.getPlayer1().getCard(0)) && checkPlayerAce(round.getPlayer1().getCard(1))){
            Utilities.askQuestion(round,Question.ONEORELEVEN);
        }

        // The fourth card is dealt to the house's hand and then ace logic is done for dealer
        round.dealCard(round.getPlayer2());
        houseAceCalculation(); // this impacts the sumplayerhand by setting the ace to 1 or 11 for the dealer

        // Both hands are then summed
        sumPlayerHand(round.getPlayer1());
        sumPlayerHand(round.getPlayer2());

        // The card information is then printed where both player cards revealed and only one dealer card revealed
        printDealtHands();

        // Check if player has blackjack immediately or busts immediately
        runPlayerBustOrBlackjack(round.getPlayer1());
    }

    public static void printDealtHands(){
        System.out.println("You have " + round.getPlayer1().getCard(0)
               + "and " + round.getPlayer1().getCard(1) + "\n" + "Sum: " + round.getPlayer1().getHandSum());
        System.out.println("House has " + round.getPlayer2().getCard(0));
    }

    // Method for dealer to determine if ace is 1 or 11 - technically they have advantage bc do it after the fact
    public static void houseAceCalculation(){
        int tempSum = 0;
        Player house = round.getPlayer2();
        for(Card card : house.getPlayerHand().getCards()){
            tempSum += card.getCardRank().getRankValue();
            if(card.getCardRank().equals(CardRank.ACE)){
                tempSum += 10;
            }
        }
        if(tempSum > 21){
            house.setAceStatus(false);
        } else{house.setAceStatus(true);}
    }

    // Method to check if the inputted card is an ace
    public static boolean checkPlayerAce(Card card){
        boolean aceOrNot = false;
        if(card.getCardRank().equals(CardRank.ACE)){aceOrNot = true;}
        return aceOrNot;
    }

    // Method to ask if the player wishes to hit or stand
    public static void askHitOrStand(){Utilities.askQuestion(round,Question.HITORSTAND);}

    // Method for playing hit or stand
    public static void playHitOrStand(){
        while(round.getPlayer1().getHandSum() < 21){
            askHitOrStand();
            if(round.getPlayer1().getHitStatus()){
                System.out.println("Player hits! ");
                round.dealCard(round.getPlayer1());
                System.out.println("Player gets: " +
                        round.getPlayer1().getPlayerHand().getCards().getLast()
                );
                sumPlayerHand(round.getPlayer1());
                System.out.println("Player has: " + round.getPlayer1().getHandSum());
                runPlayerBustOrBlackjack(round.getPlayer1());
                // Debug Line
                //System.out.println("Player hit or stand values: " + state.getGameStatus() + " | " + state.getRoundStatus());
            }else{
                System.out.println("Player chooses to stand!");
                break;
            }
        }
    }

    // Method to reveal dealer's other card
    public static void revealHouseHand(){
        boolean houseHasAce = false;

        for(Card card : round.getPlayer2().getPlayerHand().getCards()){
            if(card.getCardRank().equals(CardRank.ACE)){
                houseHasAce = true;
            }else{
                houseHasAce = false;
            }
        }

        System.out.println("House second card is: " +
                round.getPlayer2().getCard(round.getPlayer2().getPlayerHand().getCurrentSize()-1)
        );
        sumPlayerHand(round.getPlayer2());
        if(houseHasAce){
            if(round.getPlayer2().getAceStatus()){
                System.out.println("House treats Ace as 11.");
            }else{
                System.out.println("House treats Ace as 1.");
            }
        }
        System.out.println("House has " + round.getPlayer2().getHandSum());
    }

    // Method for hitting or standing dealer
    public static void houseHitOrStand(){
        sumPlayerHand(round.getPlayer2());
        if(round.getPlayer2().getHandSum() < 17){
            round.getPlayer2().setHitStatus(true);
        }else{
            round.getPlayer2().setHitStatus(false);
        }
    }

    // Method for running hitting dealer
    public static void houseHit(){
        while(round.getPlayer2().getHandSum() < 17){
            System.out.println("House has less than 17! \n House must hit.");
            if(round.getPlayer2().getHitStatus()){
                round.dealCard(round.getPlayer2());
                System.out.println("House gets: " +
                        round.getPlayer2().getPlayerHand().getCards().getLast()
                );
                sumPlayerHand(round.getPlayer2());
                System.out.println("House has: " + round.getPlayer2().getHandSum());
                runHouseBustOrBlackjack(round.getPlayer2());
                // Debug Line
                //System.out.println("House Hit or stand values: " + state.getGameStatus() + " | " + state.getRoundStatus());
            }else{
                round.getPlayer2().setHitStatus(false);
                break;
            }
        }
    }



    // Method to check who has won!
    public static void compareHands(){
        if(!round.getPlayer1().getBlackJack() && !round.getPlayer2().getBlackJack()
                && !round.getPlayer1().getBusted() && !round.getPlayer2().getBusted()){
            if(round.getPlayer1().getHandSum() > round.getPlayer2().getHandSum()){
                System.out.println("Player wins " + round.getPlayer1().getHandSum() + " to " +
                        round.getPlayer2().getHandSum());
                state.setWins(state.getWins()+1);
            } else if(round.getPlayer1().getHandSum() < round.getPlayer2().getHandSum()){
                System.out.println("Player loses " + round.getPlayer1().getHandSum() + " to " +
                        round.getPlayer2().getHandSum());
                state.setLosses(state.getLosses()+1);
            } else if(round.getPlayer1().getHandSum() == round.getPlayer2().getHandSum()){
                System.out.println("Player and House tie " + round.getPlayer1().getHandSum() + " to " +
                        round.getPlayer2().getHandSum());
                state.setTies(state.getTies()+1);
            }
        }
    }

    // The main game loop that uses all the above functions
    // REF LINE: Need the !state.getRoundStatus condition after every call to endRound() b/c otherwise never evaluates that round ends and breaks
    public static void playGameLoop(){
        initializeGame();
        while(state.getGameStatus()) {

            //Debug Lines
            //int iterations = 0;
            //System.out.println("Returned to start of loop! Iteration #" + ++iterations);

            startRound();
            if(!state.getGameStatus()){break;}
            while(state.getRoundStatus()) {
                dealCards();
                if(!state.getGameStatus()){break;}
                playHitOrStand();
                if(!state.getGameStatus() || !state.getRoundStatus()){break;} // Need the additional condition after any endRound() call: see line above
                revealHouseHand();
                if(!state.getGameStatus()){break;}
                houseHitOrStand();
                if(!state.getGameStatus()){break;}
                houseHit();
                if(!state.getGameStatus() || !state.getRoundStatus()){break;}
                compareHands();
                if(!state.getGameStatus()){break;}
                endRound();
                if(!state.getGameStatus()){break;}
            }
        }
    }
}

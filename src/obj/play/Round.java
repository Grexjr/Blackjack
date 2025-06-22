package obj.play;

import obj.cards.Deck;
import obj.choices.Choice;
import obj.exceptions.PlayerValidationException;
import obj.players.Dealer;
import obj.players.Player;

import java.util.ArrayList;

import java.lang.RuntimeException;

public class Round {

    // The two pieces of information about each round; the deck and the players - might not want to create
    // players every single round and rather have them persist with the game state?
    private Deck roundDeck;
    private Dealer dealer;
    private ArrayList<Player> players;

    // The construct for the round with one player
    public Round(Dealer dealer, Player player){
        this.roundDeck = new Deck();
        this.dealer = dealer;
        this.players = new ArrayList<Player>();
        this.players.add(player);

        this.validatePlayers();

        this.initialDeal();
    }

    // The constructor for the round with an ArrayList of players
    public Round(Dealer dealer, ArrayList<Player> players){
        this.roundDeck = new Deck();
        this.dealer = dealer;
        this.players = players;

        this.validatePlayers();

        this.initialDeal();
    }

    // Throw exception if dealer in players list
    private void validatePlayers(){
        for(Player player: this.players){
            if(player instanceof Dealer){
                throw new PlayerValidationException();
            }
        }
    }

    private void initialDeal(){
        dealer.startGame();
        for(Player player: players){
            player.startGame();
        }

        for(int i = 0; i < 2; i++) {
            for (Player player : this.getFullPlayerList()) {
                this.dealCard(player);
            }
        }
    }

    // Method to deal a card to a player, then remove that card from the deck. Maybe this should go elsewhere
    public void dealCard(Player player){
        // Adds the top card from the deck to the player's hand
        // draw card also removes it from the deck
        player.getPlayerHand().addCard(this.roundDeck.drawCard());
    }

    public ArrayList<Player> determineWinners(){
        ArrayList<Player> winners = new ArrayList<>();
        ArrayList<Player> potentialWinners = new ArrayList<>();
        int dealerScore = 0;
        int maxScore = 0;

        // Check if dealer blackjacks, if so, he instantly wins
        if(dealer.blackjack()){
            winners.add(dealer);
            return winners;
        }

        // If dealer is not busted, make his score benchmark other players have to beat and add him to potential winners
        if(!dealer.busted()){
            dealerScore = dealer.handValue();
            potentialWinners.add(dealer);
        }

        // Print player info
        for(Player player : getFullPlayerList()){
            System.out.printf(
                    "%s score %d with cards %s\n",
                    player.getName(),
                    player.handValue(),
                    player.getPlayerHand()
            );
        }

        // Loop through players to see if they win or what
        for(Player player: players){
            // Check if non-dealers have blackjack and add them to winners
            if(player.blackjack()){
                winners.add(player);
            }

            // Check if a player is not busted and if not add them to potential winners if their hand is higher than dlr
            if(!player.busted()){
                if(player.handValue() > dealerScore){
                    maxScore = player.handValue();
                    potentialWinners.add(player);
                    // Remove dealer from potential winners because hand is lower than a players'
                    // Ties already handled; player not added to potential winner if dealer ties them
                    potentialWinners.remove(dealer);
                }
            }
        }

        // If winners is not empty it means somebody got blackjack, check if that's dealer
        if(!winners.isEmpty()){ // Maybe not needed
            for(Player winner:winners){
                if(winner instanceof Dealer){
                    winners = new ArrayList<>();
                    winners.add(winner);
                    return winners;
                }
                return winners; // Do I need this return statement?
            }
        }

        // If winners is empty, go through potential winners
        for(Player winner:potentialWinners){
            // Loop through with a max score to compare scores between players
            // DON'T EXIT EARLY FOR DEALER; because then if he has lower hand he can win
            if(winner.handValue() >= maxScore){
                winners = new ArrayList<>();
                winners.add(winner);
                return winners;
            }

            return winners;
        }

        return winners;
    }

    public void takePlayerTurn(Player player){
        ArrayList<Player> otherPlayers = new ArrayList<>(this.getFullPlayerList());
        otherPlayers.remove(player);

        while(!player.isStanding() && !player.busted()) {
            Choice playerChoice = player.makeChoice(otherPlayers);
            switch (playerChoice) {
                case Choice.Hit -> {
                    System.out.printf("%s hits!\n", player.getName());
                    player.drawCard(this.roundDeck.drawCard());
                    System.out.printf("%s has cards: %s \n", player.getName(), player.visibleCards());
                }
                case Choice.Stand -> {
                    player.stand();
                }
                case Choice.Busted -> {
                    System.out.printf(
                            "%s busted with %d: %s!\n",
                            player.getName(),
                            player.handValue(),
                            player.getPlayerHand()
                    );
                }
                case Choice.Invalid -> {
                    throw new RuntimeException("INVALID CHOICE!");
                }
            }
        }
    }

    public ArrayList<Player> getFullPlayerList(){
        ArrayList<Player> allPlayers = new ArrayList<>(this.players);
        allPlayers.add(this.dealer);
        return allPlayers;
    }

    public void playRound() {
        for (Player player : this.getFullPlayerList()) {
            this.takePlayerTurn(player);
        }

        ArrayList<Player> winners = this.determineWinners();
        System.out.printf("There were %d winners!\nHere they are:\n", winners.size());

        // Printing out winners from the "Here they are:" in the above printf -- may not need
        if (!winners.isEmpty()) {
            for (Player winner : winners) {
                String winnerName = winner.getName();
                System.out.printf("%s\n", winnerName);
            }
        } else{
            System.out.print("None\n");
        }


        for (Player winner : winners) {
            winner.winGame();
        }
    }
}

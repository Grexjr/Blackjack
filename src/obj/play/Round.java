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
        int maxScore = 0;

        // Print player info
        for(Player player : getFullPlayerList()){
            System.out.printf(
                    "%s score %d with cards %s\n",
                    player.getName(),
                    player.handValue(),
                    player.getPlayerHand()
            );

            // Check if player busted, if not evaluate against last hand
            if(!player.busted()){
                if(player.handValue() > maxScore){
                    maxScore = player.handValue();
                    // Remove the last player, since they have a lower score
                    winners = new ArrayList<>();
                    winners.add(player);
                } else if(player.handValue() == maxScore){
                    winners.add(player);
                }
            }
        }
        return winners;
    }

    public void takePlayerTurn(Player player){
        ArrayList<Player> otherPlayers = new ArrayList<>(this.getFullPlayerList());
        otherPlayers.remove(player);

        while(!player.isStanding() && !player.busted()) {
            Choice playerChoice = player.makeChoice(otherPlayers);
            switch (playerChoice) {
                case Choice.HIT -> {
                    System.out.printf("%s hits!\n", player.getName());
                    player.drawCard(this.roundDeck.drawCard());
                    System.out.printf("%s has cards: %s \n", player.getName(), player.visibleCards());
                }
                case Choice.STAND -> {
                    player.stand();
                }
                case Choice.BUSTED -> {
                    System.out.printf(
                            "%s busted with %d: %s!\n",
                            player.getName(),
                            player.handValue(),
                            player.getPlayerHand()
                    );
                }
                case Choice.INVALID -> {
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

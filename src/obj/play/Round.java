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

    // TODO: refactor this function to be cleaner
    public ArrayList<Player> determineWinners(){
        ArrayList<Player> winners = new ArrayList<>();
        int maxScore = 0;

        for(Player player: this.getFullPlayerList()){
            System.out.printf(
                    "%s score %d with cards %s\n",
                    player.getName(),
                    player.handValue(),
                    player.getPlayerHand()
            );

            if(player.blackjack()){
                // if dealer has blackjack, they are the only winner
                if(player instanceof Dealer){
                    winners = new ArrayList<>();
                    winners.add(player);
                    return winners;
                }
                winners.add(player);
            }

            if(player.handValue() > maxScore && !(player.busted())){
                winners = new ArrayList<>();
                maxScore = player.handValue();
                winners.add(player);

                if(player instanceof Dealer){
                    return winners;
                }
            } else {
                if(player.handValue() == maxScore){
                    if(player instanceof Dealer) {
                        winners = new ArrayList<>();
                        winners.add(player);
                        return winners;
                    }
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
                case Choice.Hit -> {
                    System.out.printf("%s hits!\n", player.getName());
                    player.drawCard(this.roundDeck.drawCard());
                    // TODO: refactor so that player has player.visibleCards() which calls visibleCards for their hand
                    System.out.printf("%s has cards: %s \n", player.getName(), player.getPlayerHand().visibleCards());
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
        for (Player winner : winners) {
            winner.winGame();
        }
    }
}

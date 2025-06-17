package obj;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public class Round {

    // The two pieces of information about each round; the deck and the players - might not want to create
    // players every single round and rather have them persist with the game state?
    protected Deck roundDeck;
    protected Player player1,player2;

    // The constructor for the round
    public Round(){
        this.roundDeck = new Deck();
        this.player1 = new Player();
        this.player2 = new Player();
    }

    // The getters
    public Deck getRoundDeck(){return this.roundDeck;}
    public Player getPlayer1(){return this.player1;}
    public Player getPlayer2(){return this.player2;}

    // The setters

    // Method to deal a card to a player, then remove that card from the deck. Maybe this should go elsewhere
    public void dealCard(Player player){
        // Adds the top card from the deck to the player's hand
        // draw card also removes it from the deck
        player.getPlayerHand().addCard(this.roundDeck.drawCard());
    }

    public boolean roundOver(ArrayList<Player> players){
        int numFinished = 0;

        for(Player player: players){
            if(player.blackjack()){
                return true;
            }
            if(player.busted() || player.isStanding()){
                numFinished += 1;
            }
        }
        return numFinished == players.size();
    }

    public void playerChoice(Player player, Choice choice){
        if(player.isStanding()){

        }
        if(choice == Choice.Hit){
            dealCard(player);
        }
    }

    public void determineWinner(Dealer dealer, ArrayList<Player> players){
        if(dealer.blackjack()) {
            System.out.println("dealer wins with blackjack!");
        } else {
            ArrayList<Player> winners = new ArrayList<Player>();
            Player singleWinner;
            int maxScore = 0;

            for(Player player: players){
                if(player.blackjack()){
                    winners.add(player);
                }
                if(player.handValue() > maxScore){
                    maxScore = player.handValue();
                    singleWinner = player;
                }
            }

            if(winners.size() > 0){
                System.out.println("multiple winners!");
            } else {
                if(maxScore > dealer.handValue()){
                    System.out.println("player wins!");
                } else {
                    System.out.println("dealer wins...");
                }
            }
        }
    }

    public void playRound(Dealer dealer, ArrayList<Player> players){
        while(!this.roundOver(players)){
            for(Player player: players){
                Choice playerChoice = player.makeChoice(players);
                switch(playerChoice){
                    case Choice.Hit:
                        player.drawCard(this.roundDeck.drawCard());
                    case Choice.Stand:
                        player.stand();
                    case Choice.Busted:
                        System.out.println("player busted");
                }
            }
        }

        this.determineWinner(dealer, players);
    }
}

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

    public ArrayList<Player> determineWinners(ArrayList<Player> players){
        ArrayList<Player> winners = new ArrayList<Player>();
        int maxScore = 0;

        for(Player player: players){
            if(player.blackjack()){
                if(player instanceof Dealer){
                    winners = new ArrayList<Player>();
                    winners.add(player);
                    return winners;
                }
                winners.add(player);
            }

            if(player.handValue() > maxScore){
                winners = new ArrayList<Player>();
                maxScore = player.handValue();
                winners.add(player);

                if(player instanceof Dealer){
                    return winners;
                }


            } else {
                if(player.handValue() == maxScore){
                    if(player instanceof Dealer) {
                        winners = new ArrayList<Player>();
                        winners.add(player);
                        return winners;
                    }
                    winners.add(player);
                }
            }
        }

        return winners;
    }

    public void takePlayerTurn(Player player, ArrayList<Player> players){
        while(!player.isStanding() && !player.busted()) {
            Choice playerChoice = player.makeChoice(players);
            switch (playerChoice) {
                case Choice.Hit:
                    player.drawCard(this.roundDeck.drawCard());
                case Choice.Stand:
                    player.stand();
                case Choice.Busted:
                    System.out.println("player busted");
            }
        }
    }

    public void validatePlayers(ArrayList<Player> players){
        int numDealers = 0;
        for(Player player: players){
            if(player instanceof Dealer){
                numDealers += 1;
            }
        }
        if(numDealers != 1){
            // TODO: raise error
        }

        if(!(players.getLast() instanceof Dealer)){
            // TODO: raise error
        }
    }

    public void playRound(ArrayList<Player> players){
        this.validatePlayers(players);

        while(!this.roundOver(players)){
            for(Player player: players){
                this.takePlayerTurn(player, players);
            }
        }

        ArrayList<Player> winners = this.determineWinners(players);
        System.out.printf("There were %d winners!\nHere they are:\n", winners.size());
        for(Player winner: winners){
            winner.winGame();
        }
    }
}

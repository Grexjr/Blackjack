package tst;
//import arc.*;
import obj.cards.Deck;
import obj.play.Game;
import obj.players.Dealer;
import obj.players.HumanPlayer;
import obj.players.Player;

import java.util.ArrayList;

public class Tester {

    private static void testDeckCreation(){
        Deck deck = new Deck();
        System.out.println(deck.getCurrentSize());
        deck.print();
    }

    private static void testCardName(){
        Deck deck = new Deck();
        for(int i = 0; i < deck.getMaxSize(); i++) {System.out.println(deck.getCards().get(i).toString());}
    }

    private static void threePlayerGame(){

    }

    public static void main(String args[]){
        Dealer dealer = new Dealer();
        HumanPlayer player = new HumanPlayer("YOU");
        HumanPlayer player2 = new HumanPlayer("PLAYER2");
        HumanPlayer player3 = new HumanPlayer("PLAYER3");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player2);
        players.add(player3);
        Game game = new Game(dealer, players);
        game.playGame();
    }
}

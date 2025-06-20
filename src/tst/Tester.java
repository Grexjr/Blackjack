package tst;
//import arc.*;
import obj.cards.Deck;
import obj.play.Game;
import obj.players.Dealer;
import obj.players.HumanPlayer;

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

    public static void main(String args[]){
        Dealer dealer = new Dealer();
        HumanPlayer player = new HumanPlayer("YOU");
        Game game = new Game(dealer, player);
        game.playGame();
    }
}

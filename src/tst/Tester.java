package tst;
import arc.*;
import obj.*;

public class Tester {

    private static void testDeckCreation(){
        Deck deck = new Deck();
        System.out.println(deck.getCurrentSize());
        for(int i = 0; i < deck.getCurrentSize(); i++) {
            System.out.println(deck.getContents().get(i).getSuit());
            System.out.println(deck.getContents().get(i).getSuitName());
            System.out.println(deck.getContents().get(i).getValue());
            System.out.println(deck.getContents().get(i).getValueName());
            System.out.println("");
        }
    }

    private static void testCardName(){
        Deck deck = new Deck();
        for(int i = 0; i < deck.getMaxSize(); i++) {System.out.println(deck.getContents().get(i).toString());}
    }

    private static void testDealing(){
        Round round = new Round();
        System.out.println(round.getRoundDeck().getCurrentSize());
        System.out.println(round.getRoundDeck().getContents().get(0));
        round.dealCard(round.getPlayer1(),1);
        System.out.println(round.getRoundDeck().getContents().get(0));
        System.out.println(round.getPlayer1().getCard1());
        System.out.println(round.getRoundDeck().getContents().get(0));
        System.out.println(round.getRoundDeck().getCurrentSize());
        System.out.println(round.getRoundDeck().getContents().size());
    }

    private static void testChoiceFunction(int q){
        Round round = new Round();
        if(q == 1) {
            Utilities.askQuestion(round, Question.HITORSTAND);
            System.out.println(round.getHitStatus());
        }
        else if(q == 2) {
            Utilities.askQuestion(round,Question.ONEORELEVEN);
            System.out.println(round.getAceValue());
        }
    }

    private static void testGame(){
        GameLogic.playGameLoop();
    }


    public static void main(String args[]){
        //testDealing();
        //testChoiceFunction(1);
        testGame();
    }
}

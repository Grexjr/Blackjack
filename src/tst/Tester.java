package tst;
import arc.*;
import obj.*;

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

    private static void testDealing(){
        Round round = new Round();
        System.out.println(round.getRoundDeck().getCurrentSize());
        System.out.println("Top Card: " + round.getRoundDeck().getCards().get(0).getCardRank());

        round.dealCard(round.getPlayer1());
        System.out.println("Player card: " + round.getPlayer1().getPlayerHand().getCards().get(0).getCardRank());

        System.out.println("Top Card: " + round.getRoundDeck().getCards().get(0).getCardRank());

        System.out.println(round.getRoundDeck().getCurrentSize());
        System.out.println(round.getRoundDeck().getCards().size());
    }

    private static void testFullDeal(){
        Round round = new Round();
        round.dealCard(round.getPlayer1());
        if(GameLogic.checkPlayerAce(round.getPlayer1().getCard(0))){Utilities.askQuestion(round,Question.ONEORELEVEN);}
        round.dealCard(round.getPlayer2());
        round.dealCard(round.getPlayer1());
        if(!GameLogic.checkPlayerAce(round.getPlayer1().getCard(0)) && GameLogic.checkPlayerAce(round.getPlayer1().getCard(1))){
            Utilities.askQuestion(round,Question.ONEORELEVEN);
        }
        round.dealCard(round.getPlayer2());

        GameLogic.sumPlayerHand(round.getPlayer1());
        GameLogic.sumPlayerHand(round.getPlayer2());

        System.out.println("You have " + round.getPlayer1().getCard(0)
                + "and " + round.getPlayer1().getCard(1) + "\n Sum: " + round.getPlayer1().getHandSum());
        System.out.println("House has " + round.getPlayer2().getCard(0) + "and " + round.getPlayer2().getCard(1)
                + "\n Sum: " + round.getPlayer2().getHandSum());

        // Check if player has blackjack immediately or busts immediately
        GameLogic.runPlayerBustOrBlackjack(round.getPlayer1()); // throws error bc no gamestate to update
    }

    private static void testChoiceFunction(int q){
        Round round = new Round();
        if(q == 1) {
            Utilities.askQuestion(round, Question.HITORSTAND);
            //System.out.println(round.getHitStatus());
        }
        else if(q == 2) {
            Utilities.askQuestion(round,Question.ONEORELEVEN);
            //System.out.println(round.getPlayerAceEleven());
        }
    }

    private static void testGame(){
        GameLogic.playGameLoop();
    }

    private static void testGameParts(){
        GameLogic.initializeGame();
        GameLogic.startRound();
        GameLogic.dealCards();
        GameLogic.playHitOrStand();
        GameLogic.revealHouseHand();
        GameLogic.houseHitOrStand();
        GameLogic.houseHit();
        GameLogic.compareHands();
        GameLogic.endRound();
    }


    public static void main(String args[]){
        //testDealing();
        //testChoiceFunction(1);
        //testGameParts();
        //testDeckCreation();
        //testFullDeal();
        testGame();
    }
}

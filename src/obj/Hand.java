package obj;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

// This class outlines the deck object
public class Hand extends CardPile {

    // The constructor with the function to initialize it when created and to immediately shuffle the deck
    public Hand(){
        super();
    }

    // The getters
    public int getCurrentSize(){return this.cards.size();}

    protected Card playCard(Card card){
        return this.cards.remove(this.cards.indexOf(card));
    }

    protected int valueOfCards(ArrayList<Card> cards){
        int value = 0;

        ArrayList<Card> aces = this.getCardsByRank(CardRank.ACE);
        for (Card card: cards) {
            if (!aces.contains(card)) {
                value += card.getCardRank().getRankValue();
            }
            else {
                // aces default to 11
                value += 11;
            }
        }

        // aces can be value 1 if the hand value is above 21
        while(value > 21 && !aces.isEmpty()){
            value -= 10;
            aces.removeFirst();
        }

        return value;
    }

    protected ArrayList<Card> visibleCards(){
        return new ArrayList<Card>(this.cards.subList(1, this.cards.size()));
    }

    protected int visibleCardValue(){
        return valueOfCards(this.visibleCards());
    }

    protected int handValue(){
        return valueOfCards(this.cards);
    }
}

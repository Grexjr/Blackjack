package obj;

import java.util.ArrayList;
import java.util.Collections;

// This class outlines the deck object
public class CardPile {

    // The two pieces of information about the deck; its contents and its size
    ArrayList<Card> cards;

    // The constructor with the function to initialize it when created and to immediately shuffle the deck
    public CardPile(){
        this.cards = new ArrayList<Card>();
    }

    // The getters
    public ArrayList<Card> getCards(){return this.cards;}
    public int getCurrentSize(){return this.cards.size();}

    protected boolean hasCard(Card card){
        return this.cards.contains(card);
    }

    protected void addCard(Card card){
        this.cards.add(card);
    }

    protected ArrayList<Card> getCardsByRank(CardRank rank){
        ArrayList<Card> rankCards = new ArrayList<Card>();
        for (Card card: this.cards){
            if(card.getCardRank() == rank){
                rankCards.add(card);
            }
        }
        return rankCards;
    }

    protected Card drawCard(){
        return this.cards.removeFirst();
    }

    // The method to shuffle the deck into a random configuration once it has been initialized
    protected void shuffleCards(){Collections.shuffle(this.cards);}


    // Testing method to print the deck
    public void print() {
        for (Card card : this.cards) {
            System.out.println(card.getCardRank().getRankName() + card.getCardSuit().getSuitName());
            System.out.println(card.getCardRank().getRankValue());
        }
    }
}

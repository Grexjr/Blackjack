package obj;

import java.util.ArrayList;
import java.util.Collections;

// This class outlines the deck object
public class Deck {

    // The two pieces of information about the deck; its contents and its size
    ArrayList<Card> contents;
    int maxSize, currentSize;

    // The constructor with the function to initialize it when created and to immediately shuffle the deck
    public Deck(){
        this.maxSize = 52;
        this.contents = new ArrayList<Card>();
        initializeDeck();
        shuffleDeck();
        this.currentSize = this.contents.size();
    }

    // The getters
    public int getMaxSize(){return this.maxSize;}
    public ArrayList<Card> getContents(){return this.contents;}
    public int getCurrentSize(){return this.currentSize;}

    // Method to update currentSize
    public void updateCurrentSize(){this.currentSize = this.contents.size();}

    // The method to initialize the deck with the new card system
    protected void initializeDeck(){
        for(CardSuit suit : CardSuit.values()){
            for(CardRank rank : CardRank.values()){
                this.contents.add(new Card(rank,suit));
            }
        }
    }

    // The method to shuffle the deck into a random configuration once it has been initialized
    protected void shuffleDeck(){Collections.shuffle(this.contents);}


    // Testing method to print the deck
    public void printDeck() {
        for (Card card : contents) {
            System.out.println(card.getCardRank().getRankName() + card.getCardSuit().getSuitName());
            System.out.println(card.getCardRank().getRankValue());
        }
    }
}

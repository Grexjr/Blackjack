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

    // The method to initialize the deck by first adding the spades, then the clubs, etc; could be made better with recursion
    protected void initializeDeck(){
        for(int i = 0; i < (this.maxSize /4); i++){ // Spades
            this.contents.add(new Card(i + 1,1));
        }
        for(int i = 0; i < (this.maxSize /4); i++){ // Clubs
            this.contents.add(new Card(i + 1,2));
        }
        for(int i = 0; i < (this.maxSize /4); i++){ // Hearts
            this.contents.add(new Card(i + 1,3));
        }
        for(int i = 0; i < (this.maxSize /4); i++){ // Diamonds
            this.contents.add(new Card(i + 1,4));
        }
    }

    // The method to shuffle the deck into a random configuration once it has been initialized
    protected void shuffleDeck(){Collections.shuffle(this.contents);}
}

package obj.cards;

// This class outlines the deck object
public class Deck extends CardPile {

    int maxSize = 52;

    // The constructor with the function to initialize it when created and to immediately shuffle the deck
    public Deck(){
        super();
        initializeDeck();
        this.shuffleCards();
    }

    // The getters
    public int getMaxSize(){return this.maxSize;}

    // The method to initialize the deck with the new card system
    protected void initializeDeck(){
        for(CardSuit suit : CardSuit.values()){
            for(CardRank rank : CardRank.values()){
                this.addCard(new Card(rank, suit));
            }
        }
    }

    @Override
    public void addCard(Card card){
        if(!this.hasCard(card)){
            this.cards.add(card);
        }
    }
}

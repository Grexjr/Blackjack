package obj;

// The class outlining the card object - need to deal with ace either being 1 or 11, and all face cards being 10
public class Card {

    // The four pieces of data about the card; its value and its suit, and their names.
    // Suit is stored as an integer for 1,2,3,4 to represent each suit; Spades, Clubs, Hearts, Diamonds
    protected CardRank cardRank;
    protected CardSuit cardSuit;

    // Card constructor
    public Card(CardRank rank, CardSuit suit){
        this.cardRank = rank;
        this.cardSuit = suit;
    }

    // Getter functions
    public CardSuit getCardSuit(){return this.cardSuit;}
    public CardRank getCardRank(){return this.cardRank;}

    @Override
    public boolean equals(Object obj){
        //override equals so that cards are equal based on suit and rank
        // if the comparison object is null, it does not equal this one
        if(obj == null){
            return false;
        }
        // if the comparison object is not a card, it does not equal this one
        if(obj.getClass() != this.getClass()) {
            return false;
        }
        // cast the comparison object to a card
        final Card card = (Card) obj;
        // cards are seen as equivalent with same rank and suit
        return card.cardRank == this.cardRank && card.cardSuit == this.cardSuit;
    }

    // Card toString
    @Override
    public String toString(){
        String cardFullName = this.getCardRank().getRankName() + this.getCardSuit().getSuitName();
        return cardFullName;
    }

}
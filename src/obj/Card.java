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

    // Card toString
    @Override
    public String toString(){
        String cardFullName = this.getCardRank().getRankName() + this.getCardSuit().getSuitName();
        return cardFullName;
    }

}
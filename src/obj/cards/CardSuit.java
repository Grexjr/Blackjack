package obj.cards;

// Enum for card suit
public enum CardSuit {
    // Enum suit values
    SPADE("Spades "),
    CLUB("Clubs "),
    HEART("Hearts "),
    DIAMOND("Diamonds ");

    // Declaration of enum constants
    private final String suitName;

    // CardSuit enum constructor
    CardSuit(String name){this.suitName = name;}

    // Getter functions
    public String getSuitName(){return suitName;}
}
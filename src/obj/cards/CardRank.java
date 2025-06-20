package obj.cards;

// Enum for card rank
public enum CardRank {
    // Rank values
    ACE("Ace of ", 1),
    TWO("Two of ", 2),
    THREE("Three of ", 3),
    FOUR("Four of ", 4),
    FIVE("Five of ", 5),
    SIX("Six of ", 6),
    SEVEN("Seven of ", 7),
    EIGHT("Eight of ", 8),
    NINE("Nine of ", 9),
    TEN("Ten of ", 10),
    JACK("Jack of ", 10),
    QUEEN("Queen of ", 10),
    KING("King of ", 10);

    // Declaration of constants
    private final String rankName;
    private final int rankValue;

    // Constructor of enum
    CardRank(String name,int value){
        this.rankName = name;
        this.rankValue = value;
    }

    // Getter functions
    public String getRankName(){return rankName;}
    public int getRankValue(){return rankValue;}

}
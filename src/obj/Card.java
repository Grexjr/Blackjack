package obj;

// The class outlining the card object - need to deal with ace either being 1 or 11, and all face cards being 10
public class Card {

    // The four pieces of data about the card; its value and its suit, and their names.
    // Suit is stored as an integer for 1,2,3,4 to represent each suit; Spades, Clubs, Hearts, Diamonds
    protected int value, suit;
    protected String valueName, suitName;

    // Card constructor
    public Card(int num, int suit){
        this.value = num;
        this.suit = suit;
        this.suitName = createSuitName();
        this.valueName = createValueName();
    }

    // Getter functions
    public int getValue(){return this.value;}
    public int getSuit(){return this.suit;}
    public String getValueName(){return this.valueName;}
    public String getSuitName(){return this.suitName;}

    // Converts integer index for suit into the string suit name; can be simplified with -> syntax.
    protected String createSuitName(){
        switch(this.suit) {
            case 1:
                return "Spades ";
            case 2:
                return "Clubs ";
            case 3:
                return "Hearts ";
            case 4:
                return "Diamonds ";
            default:
                return "ERROR"; // Card without proper suit index
        }
    }

    // Converts integer numerical value to the string value name.
    protected String createValueName(){
        if(this.value == 1){
            return "Ace of ";
        } else if(this.value > 1 && this.value <= 10){
            return Integer.toString(this.value) + " of ";
        } else if(this.value == 11){
            return "Jack of ";
        } else if(this.value == 12){
            return "Queen of ";
        } else if(this.value == 13){
            return "King of ";
        } else{
            return "ERROR"; // Card without proper value
        }
    }

    // toString function of the card to print out its proper string name in full
    @Override
    public String toString(){
        String cardNameFull = this.valueName + this.suitName;
        return cardNameFull;
    }


}

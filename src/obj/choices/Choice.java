package obj.choices;

// Enum for card rank
public enum Choice {
    // Rank values
    HIT("Hit"),
    STAND("Stand"),
    // TODO: actually implement split logic
    SPLIT("Split"),
    BUSTED("Busted"),
    INVALID("Invalid");

    // Declaration of constants
    private final String choice;

    // Constructor of enum
    Choice(String choice){
        this.choice = choice;
    }

}
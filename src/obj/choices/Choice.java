package obj.choices;

// Enum for card rank
public enum Choice {
    // Rank values
    Hit("Hit"),
    Stand("Stand"),
    // TODO: actually implement split logic
    Split("Split"),
    Busted("Busted"),
    Invalid("Invalid");

    // Declaration of constants
    private final String choice;

    // Constructor of enum
    Choice(String choice){
        this.choice = choice;
    }

}
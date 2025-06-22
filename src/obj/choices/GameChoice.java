package obj.choices;

// Enum for card rank
public enum GameChoice {
    // Rank values
    CONTINUE("Continue"),
    STOP("Stop"),
    INVALID("Invalid");

    // Declaration of constants
    private final String choice;

    // Constructor of enum
    GameChoice(String choice){
        this.choice = choice;
    }

}
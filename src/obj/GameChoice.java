package obj;

// Enum for card rank
public enum GameChoice {
    // Rank values
    Continue("Continue"),
    Stop("Stop"),
    Invalid("Invalid");

    // Declaration of constants
    private final String choice;

    // Constructor of enum
    GameChoice(String choice){
        this.choice = choice;
    }

}
package obj.choices;

    /* Enum to create the different questions, can have different numbers of parameters for different
    questions, but need a constructor for each variation. Should this be moved to a different class? */
    public enum Question{
        // Enum question values
        HIT_OR_STAND("Hit or Stand?", "1: Hit", "2: Stand"),
        ONE_OR_ELEVEN("Set Ace as one or eleven?", "1: One", "2: Eleven"),
        CONTINUE_OR_NOT("Continue?","1: Yes","2:No");

        // Declaration of enum constants
        private final String question;

        // Question enum constructor
        Question(String question, String answer1, String answer2){
            this.question = question;
        }

        // Getter functions for the enum
        public String getQuestion(){return question;}

        @Override
        public String toString(){
            return this.question;
        }
    }

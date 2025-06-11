package arc;

    /* Enum to create the different questions, can have different numbers of parameters for different
    questions, but need a constructor for each variation. Should this be moved to a different class? */
    public enum Question{
        // Enum question values
        HITORSTAND("Hit or Stand?", "1: Hit", "2: Stand"),
        ONEORELEVEN("Set Ace as one or eleven?", "1: One", "2: Eleven"),
        CONTINUEORNOT("Continue?","1: Yes","2:No");

        // Declaration of enum constants
        private final String question,answerOne,answerTwo;

        // Question enum constructor
        Question(String question, String answer1, String answer2){
            this.question = question;
            this.answerOne = answer1;
            this.answerTwo = answer2;
        }

        // Getter functions for the enum
        public String getQuestion(){return question;}
        public String getAnswerOne(){return answerOne;}
        public String getAnswerTwo(){return answerTwo;}
    }

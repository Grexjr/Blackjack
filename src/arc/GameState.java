package arc;

public class GameState {

    // The variables tracked by the overall single game state that persists between rounds
    int handCounter, wins, losses, ties;
    double winPercent;
    boolean gameStatus;

    // The constructor
    public GameState(){
        this.gameStatus = true;
        this.handCounter = 0;
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;
        this.winPercent = calcWinPercent();
    }

    // The getters
    public boolean getGameStatus(){return this.gameStatus;}
    public int getHandCounter(){return this.handCounter;}
    public int getWins(){return this.wins;}
    public int getLosses(){return this.losses;}
    public int getTies(){return this.ties;}
    public double getWinPercent(){return this.winPercent;}

    // The setters
    protected void setGameStatus(boolean status){this.gameStatus = status;}
    protected void setHandCounter(int hand){this.handCounter = hand;}
    protected void setWins(int i){this.wins = i;}
    protected void setLosses(int i){this.losses = i;}
    protected void setTies(int i){this.ties = i;}

    // The calculation for win percent - makes sure not to divide by zero
    protected double calcWinPercent(){
        if(losses + ties == 0) {return 0;}
        else{return wins / (losses + ties);}
    }

    // The toString of the game state to be printed at the end of every round
    @Override
    public String toString(){
        String handString = "Round: " + this.handCounter + "\n \n";
        String winsString = "Wins: " + this.wins + "\n";
        String lossesString = "Losses: " + this.losses + "\n";
        String tiesString = "Ties: " + this.ties + "\n";
        String winPercentString = "Win Percentage: " + this.winPercent + "\n";
        String gameStateString = handString + winsString + lossesString + tiesString + winPercentString;
        return gameStateString;
    }
}

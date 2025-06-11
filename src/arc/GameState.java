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
        this.winPercent = 0;
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
    protected void setWinPercent(double d){this.winPercent = d;}

    // The calculation for win percent - makes sure not to divide by zero
    protected void calcWinPercent(){
        if(this.losses + this.ties == 0) {this.setWinPercent(0);}
        else{this.setWinPercent((this.wins / (this.losses + this.ties)) * 100);}
    }

    // The toString of the game state to be printed at the end of every round
    @Override
    public String toString(){
        String handString = "Round: " + this.handCounter + "\n \n";
        String winsString = "Wins: " + this.wins + "\n";
        String lossesString = "Losses: " + this.losses + "\n";
        String tiesString = "Ties: " + this.ties + "\n";
        this.calcWinPercent();
        String winPercentString = "Win Percentage: " + this.winPercent + "\n";
        String gameStateString = handString + winsString + lossesString + tiesString + winPercentString;
        return gameStateString;
    }
}

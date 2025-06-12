package arc;

public class GameState {

    // The variables tracked by the overall single game state that persists between rounds
    int handCounter, wins, losses, ties;
    double winPercent;
    boolean gameStatus,roundStatus;

    // The constructor
    public GameState(){
        this.gameStatus = true;
        this.roundStatus = false;
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
    public boolean getRoundStatus(){return this.roundStatus;}

    // The setters
    protected void setGameStatus(boolean status){this.gameStatus = status;}
    protected void setRoundStatus(boolean status){this.roundStatus = status;}
    protected void setHandCounter(int hand){this.handCounter = hand;}
    protected void setWins(int i){this.wins = i;}
    protected void setLosses(int i){this.losses = i;}
    protected void setTies(int i){this.ties = i;}
    protected void setWinPercent(double d){this.winPercent = d;}

    // The calculation for win percent - makes sure not to divide by zero
    protected void calcWinPercent(){
        if(this.wins + this.losses + this.ties == 0) {this.setWinPercent(0);}
        else{
            //Debug Line
            //System.out.println("Reached the else!");

            this.setWinPercent(((double)this.wins / (double)(this.wins + this.losses + this.ties)) * 100);
        }
    }

    // The toString of the game state to be printed at the end of every round
    @Override
    public String toString(){
        String handString = "Round: " + this.getHandCounter() + "\n \n";
        String winsString = "Wins: " + this.getWins() + "\n";
        String lossesString = "Losses: " + this.getLosses() + "\n";
        String tiesString = "Ties: " + this.getTies() + "\n";
        this.calcWinPercent();
        String winPercentString = "Win Percentage: " + this.getWinPercent() + "\n";
        String gameStateString = handString + winsString + lossesString + tiesString + winPercentString;
        return gameStateString;
    }
}

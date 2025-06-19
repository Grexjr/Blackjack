package obj;
import java.util.ArrayList;
import java.util.UUID;

public class Player {

    // The only piece of information about the player; the arraylist of their cards
    private Hand playerHand;
    private boolean standing;
    private int numWins, numGames;
    private String name;

    // The constructor; null values aren't the best, but will never be referenced
    public Player(){
        this.playerHand = new Hand();
        this.standing = false;
        this.numWins = 0;
        this.numGames = 0;
        this.name = UUID.randomUUID().toString();
    }

    public Player(String name){
        this.playerHand = new Hand();
        this.standing = false;
        this.numWins = 0;
        this.numGames = 0;
        this.name = name;
    }

    // The getters
    public Hand getPlayerHand(){return this.playerHand;}
    public Card getCard(int index){return this.playerHand.getCards().get(index);}
    public void drawCard(Card card){this.playerHand.addCard(card);}
    public String getName(){return this.name;}
    public void startGame(){
        this.playerHand = new Hand();
        this.numGames += 1;
        this.standing = false;
    }

    public boolean busted(){return this.playerHand.handValue() > 21;}
    public boolean blackjack(){return this.playerHand.handValue() == 21;}
    public int handValue(){return this.playerHand.handValue();}


    public void stand(){
        System.out.printf("%s stands\n", this.name);
        this.standing = true;
    }
    public boolean isStanding(){return this.standing;}

    public CardPile visibleCards(){
        return new CardPile(this.playerHand.visibleCards());
    }

    public String showVisibleCards(){
        return String.format("%s : %s", this.name, this.visibleCards());
    }

    public Choice makeChoice(ArrayList<Player> opponents){
        if(this.busted()){
            System.out.printf("%s busted!\n", this.name);
            return Choice.Busted;
        }

        if(this.standing){
            System.out.printf("%s is standing...", this.name);
            return Choice.Stand;
        }
        return Choice.Invalid;
    }

    public float getWinPercentage(){
        return ((float) this.numWins ) / ((float) this.numGames) * 100;
    }

    public void winGame(){
        this.numWins += 1;

        System.out.printf(
                "\nPlayer %s wins with score %d\nTheir win percentage: %.2f\n\n",
                this.name,
                this.handValue(),
                this.getWinPercentage()
        );
    }
}

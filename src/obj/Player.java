package obj;
import java.util.ArrayList;

public class Player {

    // The only piece of information about the player; the arraylist of their cards
    private ArrayList<Card> playerHand;
    private int handSum;
    private boolean hitstatus, acestatus, busted, blackjack;

    // The constructor; null values aren't the best, but will never be referenced
    public Player(){
        this.playerHand = new ArrayList<Card>();
        this.handSum = 0;
        this.hitstatus = false;
        this.acestatus = false;
        this.busted = false;
        this.blackjack = false;
    }

    // The getters
    public ArrayList<Card> getPlayerHand(){return this.playerHand;}
    public Card getCard(int index){return this.playerHand.get(index);}
    public int getHandSum(){return this.handSum;}
    public boolean getBusted(){return this.busted;}
    public boolean getHitStatus(){return this.hitstatus;}
    public boolean getAceStatus(){return this.acestatus;}
    public boolean getBlackJack(){return this.blackjack;}

    // The setters
    public void setHandSum(int sum){this.handSum = sum;}
    public void setBusted(boolean val){this.busted = val;}
    public void setHitStatus(boolean val){this.hitstatus = val;}
    public void setAceStatus(boolean val){this.acestatus = val;}
    public void setBlackjack(boolean val){this.blackjack = val;}
}

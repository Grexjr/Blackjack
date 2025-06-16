package obj;
import java.util.ArrayList;

public class Player {

    // The only piece of information about the player; the arraylist of their cards
    private Hand playerHand;
    private boolean hitstatus, acestatus, standing;

    // The constructor; null values aren't the best, but will never be referenced
    public Player(){
        this.playerHand = new Hand();
        this.hitstatus = false;
        this.acestatus = false;
        this.standing = false;
    }

    // The getters
    public Hand getPlayerHand(){return this.playerHand;}
    public Card getCard(int index){return this.playerHand.getCards().get(index);}

    public boolean busted(){return this.playerHand.handValue() > 21;}
    public boolean blackjack(){return this.playerHand.handValue() == 21;}
    public int handValue(){return this.playerHand.handValue();}

    public boolean getHitStatus(){return this.hitstatus;}
    public boolean getAceStatus(){return this.acestatus;}
    public boolean isStanding(){return this.standing;}

    public void setHitStatus(boolean val){this.hitstatus = val;}
    public void setAceStatus(boolean val){this.acestatus = val;}
}

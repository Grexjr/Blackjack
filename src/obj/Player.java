package obj;
import java.util.ArrayList;

public class Player {

    // The only piece of information about the player; the arraylist of their cards
    private Hand playerHand;
    private boolean hitstatus, acestatus, standing;

    // The constructor; null values aren't the best, but will never be referenced
    public Player(){
        this.playerHand = new Hand();
        this.standing = false;
    }

    // The getters
    public Hand getPlayerHand(){return this.playerHand;}
    public Card getCard(int index){return this.playerHand.getCards().get(index);}
    public void drawCard(Card card){this.playerHand.addCard(card);}


    public boolean busted(){return this.playerHand.handValue() > 21;}
    public boolean blackjack(){return this.playerHand.handValue() == 21;}
    public int handValue(){return this.playerHand.handValue();}


    public void stand(){this.standing = true;}
    public boolean isStanding(){return this.standing;}

    public CardPile visibleCards(){
        return new CardPile(this.playerHand.visibleCards());
    }

    public Choice makeChoice(ArrayList<Player> opponents){
        if(this.busted()){
            return Choice.Busted;
        }

        if(this.standing){
            return Choice.Stand;
        }
        return Choice.Invalid;
    }
}

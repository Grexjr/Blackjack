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
        this.name = name;
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

    public float getWinPercentage(){
        return ((float) this.numWins ) / ((float) this.numGames) * 100;
    }

    public void winGame(){
        System.out.printf(
                "Player %s wins!%nWin percentage: %.2f\n",
                this.name,
                this.getWinPercentage()
        );
    }
}

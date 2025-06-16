package obj;

import java.util.ArrayList;

public class Dealer extends Player{
    public Choice makeChoice(ArrayList<Player> opponents){
        int dealerHandValue = this.getPlayerHand().handValue();

        if(this.blackjack()){
            return Choice.Stand;
        }

        for(Player player: opponents){
            if(dealerHandValue < player.getPlayerHand().handValue()){
                return Choice.Hit;
            }
        }

        return Choice.Stand;
    }
}

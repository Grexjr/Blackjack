package obj;

import java.util.ArrayList;

public class Dealer extends Player{
    final int DEALER_MINIMUM_VALUE = 17;

    public Choice makeChoice(ArrayList<Player> opponents){
        if(this.blackjack()){
            return Choice.Stand;
        }

        for(Player player: opponents){
            if((!player.busted() && this.handValue() < DEALER_MINIMUM_VALUE) || player.blackjack()){
                return Choice.Hit;
            }
        }

        return Choice.Stand;
    }
}

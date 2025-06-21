package obj.players;

import obj.choices.Choice;

import java.util.ArrayList;

public class Dealer extends Player{
    final int DEALER_MINIMUM_VALUE = 17;
    public Dealer(){
        super("DEALER");
    }

    @Override
    public Choice makeChoice(ArrayList<Player> opponents){
        Choice choice = super.makeChoice(opponents);

        if(choice != Choice.Invalid){
            return choice;
        }

        if(this.blackjack()){
            return Choice.Stand;
        }

        for(Player player: opponents){
            // TODO: determine proper dealer hit/stand logic
            if((player.handValue() >= this.handValue() || player.blackjack()) && !this.busted()){
                return Choice.Hit;
            }
        }

        return Choice.Stand;
    }
}

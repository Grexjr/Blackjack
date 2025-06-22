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

        if(choice != Choice.INVALID){
            return choice;
        }

        if(this.blackjack()){
            return Choice.STAND;
        }

        //Dealer must hit if below 17, must stand if above 17
        if(this.handValue() < DEALER_MINIMUM_VALUE){
            return Choice.HIT;
        }

        return Choice.STAND;
    }
}

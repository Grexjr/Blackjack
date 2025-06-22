package obj.players;

import obj.choices.Choice;

import java.util.ArrayList;

public class NPCPlayer extends Player{
    // Constructor using the UUID player constructor
    public NPCPlayer(){}

    // NPC Player AI to make choice
    // TODO: Add separate NPC AI separate from the Dealer
    @Override
    public Choice makeChoice(ArrayList<Player> opponents){
        Choice choice = super.makeChoice(opponents);

        if(choice != Choice.INVALID){
            return choice;
        }

        if(this.blackjack()){
            return Choice.STAND;
        }

        if(this.handValue() < 16){
            return Choice.HIT;
        }

        return Choice.STAND;
    }
}

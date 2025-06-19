package obj;

import arc.Question;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(String name){
        super(name);
    }

    public Choice detectChoice(){
        Scanner kb = new Scanner(System.in);
        String choice = kb.nextLine();

        if(choice.equalsIgnoreCase("hit") || choice.equals("1") || choice.equalsIgnoreCase("h")){
            System.out.println("player chose to hit");
            return Choice.Hit;
        }
        if(choice.equalsIgnoreCase("stand") || choice.equals("2") || choice.equalsIgnoreCase("s")){
            System.out.println("player chose to stand");
            return Choice.Stand;
        }

        System.out.println("Invalid choice! Please enter either ('Hit'/'1') or ('Stand'/'2')");
        return Choice.Invalid;
    }

    @Override
    public Choice makeChoice(ArrayList<Player> opponents){
        Choice choice = super.makeChoice(opponents);
        System.out.printf("You have the following cards: %s\n", this.getPlayerHand());
        System.out.printf("Your hand is worth %d points\n", this.handValue());

        System.out.println("Opponents have the following cards:");
        for(Player player: opponents){
            System.out.println(player.showVisibleCards());
        }

        while(choice == Choice.Invalid){
            System.out.println(Question.HITORSTAND.getQuestion());
            choice = this.detectChoice();
        }

        return choice;
    }
}

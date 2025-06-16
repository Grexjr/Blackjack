package obj;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {
    public Choice detectChoice(){
        Scanner kb = new Scanner(System.in);
        String choice = kb.next();

        if(choice.equalsIgnoreCase("hit") || choice.equals("1")){
            return Choice.Hit;
        }
        if(choice.equalsIgnoreCase("stand") || choice.equals("2")){
            return Choice.Stand;
        }

        System.out.println("Invalid choice! Please enter either ('Hit'/'1') or ('Stand'/'2')");
        return Choice.Invalid;
    }

    public Choice makeChoice(ArrayList<Player> opponents){
        Choice choice = Choice.Invalid;


        System.out.println("Opponents have the following cards:");
        for(Player player: opponents){
            System.out.println(player.visibleCards().toString());
        }

        while(choice == Choice.Invalid){
            System.out.println("Player: Hit or Stand?");
            choice = this.detectChoice();
        }

        return choice;

    }
}

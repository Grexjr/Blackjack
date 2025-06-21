package obj.play;

import obj.choices.Question;
import obj.choices.GameChoice;
import obj.players.Dealer;
import obj.players.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Dealer dealer;
    ArrayList<Player> players;

    public Game(Dealer dealer, ArrayList<Player> players){
        this.dealer = dealer;
        this.players = players;
    }

    public Game(Dealer dealer, Player player){
        this.dealer = dealer;
        this.players = new ArrayList<>();
        this.players.add(player);
    }

    private void playRound(){
        // TODO: allow games to track an arraylist of rounds, in case a user wants to see the overall game summary
        Round round = new Round(this.dealer, this.players);
        round.playRound();
    }

    // TODO: abstract out the choice logic a bit here and for the player detect choice
    private GameChoice detectContinueChoice(){
        Scanner kb = new Scanner(System.in);
        String choice = kb.nextLine();

        if(choice.equalsIgnoreCase("continue") || choice.equals("1") || choice.equalsIgnoreCase("c")){
            System.out.println("continuing play...");
            return GameChoice.Continue;
        }
        if(choice.equalsIgnoreCase("stop") || choice.equals("2") || choice.equalsIgnoreCase("s")){
            System.out.println("ending play...");
            return GameChoice.Stop;
        }

        System.out.println("Invalid choice! Please enter either ('Continue'/'1') or ('Stop'/'2')");
        return GameChoice.Invalid;
    }

    public void playGame(){
        GameChoice choice;
        do {
            // initialize choice as invalid so that we prompt for the user choice later
            choice = GameChoice.Invalid;
            // play the game round
            playRound();

            // detect if player wants to continue the game
            while(choice == GameChoice.Invalid){
                System.out.println(Question.CONTINUEORNOT);
                choice = this.detectContinueChoice();
            }
        } while(choice == GameChoice.Continue);

        System.out.println("Game Over!");
    }
}

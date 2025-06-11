package arc;

import java.util.InputMismatchException;
import java.util.Scanner;
import obj.*;

public class Utilities {

    // Static methods for general utilities; player input, etc.

    // General format for asking questions
    // Only does 2 answers, might need a way to iterate using an array if more than two answers are possible
    // Maybe put this into the question file
    public static void askQuestion(Round round, Question question){
        System.out.println(question.getQuestion());
        System.out.println(question.getAnswerOne());
        System.out.println(question.getAnswerTwo());

        int choice = playerInputOpportunity();

        if(question.equals(Question.HITORSTAND)){
            switch(choice){
                case 1:
                    round.getPlayer1().setHitStatus(true);
                    break;
                case 2:
                    round.getPlayer1().setHitStatus(false);
                    break;
                default:
                    System.out.println("Invalid input!");
                    askQuestion(round,question);
                    break;
            }
        } else if(question.equals(Question.ONEORELEVEN)){
            switch(choice){
                case 1:
                    round.getPlayer1().setAceStatus(false);
                    break;
                case 2:
                    round.getPlayer1().setAceStatus(true);
                    break;
                default:
                    System.out.println("Invalid input!");
                    askQuestion(round,question);
                    break;
            }
        } else if(question.equals(Question.CONTINUEORNOT)){
            switch(choice){
                case 1:
                    GameLogic.continueGame();
                    break;
                case 2:
                    GameLogic.endGame(); // Sometimes will ask twice about continuing and print state, not sure why
                    break;
                default:
                    System.out.println("Invalid input!");
                    askQuestion(round,question);
            }
        }
    }

    // Method for asking for player input
    public static int playerInputOpportunity(){
        Scanner kb = new Scanner(System.in);
        int choice = -1;
        try {
            choice = kb.nextInt();
        } catch(InputMismatchException e){
            System.out.println("Please input a number!");
            kb.next();
            return playerInputOpportunity();
        }

        return choice;
    }


}

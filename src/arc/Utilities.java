package arc;

import java.util.InputMismatchException;
import java.util.Scanner;
import obj.*;

public class Utilities {

    // Static methods for general utilities; player input, etc.

    // General format for asking questions
    // Only does 2 answers, might need a way to iterate using an array if more than two answers are possible
    public static void askQuestion(Round round, Question question){
        System.out.println(question.getQuestion());
        System.out.println(question.getAnswerOne());
        System.out.println(question.getAnswerTwo());

        int choice = playerInputOpportunity();

        if(question.equals(Question.HITORSTAND)){
            switch(choice){
                case 1:
                    round.setHitStatus(true);
                    break;
                case 2:
                    round.setHitStatus(false);
                    break;
                default:
                    System.out.println("Invalid input!");
                    askQuestion(round,question);
                    break;
            }
        } else if(question.equals(Question.ONEORELEVEN)){
            switch(choice){
                case 1:
                    round.setAceValue(1);
                    break;
                case 2:
                    round.setAceValue(11);
                    break;
                default:
                    System.out.println("Invalid input!");
                    askQuestion(round,question);
                    break;
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

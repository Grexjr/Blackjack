package arc;

import java.util.Scanner;
import obj.*;

public class Utilities {

    // Static methods for general utilities; player input, etc.
    private static String questionBeingAsked;

    // Method for asking for player input - only hardcoded for hit or stand right now but can probably use array
    // To do things with more options and different text
    // May need an exception catcher for if a non-number is inputted
    public static int askPlayerInput(String question){
        questionBeingAsked = question;
        Scanner kb = new Scanner(System.in);
        System.out.println(question);
        System.out.println("1: Hit");
        System.out.println("2: Stand");
        int choice = kb.nextInt();
        return choice;
    }

    // Method for changing hit status depending on answer - using recursion ;) - add try/catch around
    // to ensure only numbers are put in; try this code, if catches, then recurse? not sure if that will work.
    public static void hitOrNo(int choice, Round round){
        if(choice == 1){
            round.setHitStatus(true);
        } else if(choice == 2){
            round.setHitStatus(false);
        } else{
            System.out.println("Invalid input!");
            hitOrNo(askPlayerInput(questionBeingAsked),round);
        }
    }








}

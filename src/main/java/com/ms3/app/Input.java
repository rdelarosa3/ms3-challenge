package com.ms3.app;

import java.util.Scanner;

public class Input {
    private static Scanner scanner;

    /** returns a string from user input **/
    public static String getString(){
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /** returns a boolean from user input **/
    public static boolean yesNo(){
        scanner = new Scanner(System.in);
        System.out.println(ANSI_YELLOW+"[Y/n]"+ANSI_RESET);
        String userResponse = scanner.nextLine();
        if (userResponse.trim().toLowerCase().startsWith("y")){
            return true;
        }
        else if(userResponse.trim().toLowerCase().startsWith("n")){
            return false;
        }
        System.err.println("Enter a correct value: ");
        return yesNo();
    }

    /** returns an integer value from users input **/
    public static int getInt(){
        try{
            return Integer.parseInt(getString());
        }catch (Exception ex){
            System.err.println("Enter a correct value: ");
            return getInt();
        }
    }

    /** returns an integer value from a range based users input **/
    public static int getInt(int min, int max){
        int userNumber = getInt();
        if (userNumber >= min && userNumber <= max){
            return userNumber;
        }else{
            System.err.printf("Enter a number between %s and %s: ", min, max);
            return getInt(min, max);
        }
    }

    /** color for interface **/
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\033[0;33m";

}

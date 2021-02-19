package com.ms3.app;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class App {
    private static File file; // gets file from input
    private static String fname; // file name to use for db,table,directory
    private static final String[] menuItems = {"Import CSV", "Exit"}; // menu items for cli

    public static void main(String[] args) {
        boolean runProgram = true;
        while (runProgram){
            printMenu();
            runProgram = getMenuInput();
        }
        System.out.println("Program Terminated");
    }

    /** creates a directory based on the file name to store db, logfile and csv **/
    public static void createDirectory(String fname){
        File directory = new File(fname);
        if(!directory.exists()) {
            if(directory.mkdir()){
                System.out.println("Creating Directory");
                parseDataToDB();
            }else{
                System.err.println("Unable to create Directory");
            }
        }else{
            System.err.println("Directory Already Exists. OverWrite?");
            if(Input.yesNo()) {
                parseDataToDB();
            }
        }
    }

    /** interface for to acknowledge user **/
    public static void printMenu(){
        System.out.println(("What would you like to do?"));
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i]);
        }
    }

    /** gets the users input from menu items **/
    public static boolean getMenuInput(){
        System.out.println("Enter an option (1 through "+menuItems.length+"):");
        // validate user input
        switch (Input.getInt(1,menuItems.length)){
            case 1:
                getCSVFile();
                break;
            case 2:
                return false;
            default:
                break;
        }
        return true;
    }

    /** gets user csv from user input **/
    public static void getCSVFile(){
        System.out.println("Enter your CSV file path: ");
        String input = Input.getString(); // scanner to get user input
        File tempFile = new File(input); // gets file from input
        if (!tempFile.getName().toLowerCase().endsWith(".csv")){    // verifies file is CSV extension
            System.err.println("Invalid File Type CSV Required");
        }else{
            file = tempFile;
            fname = FilenameUtils.removeExtension(file.getName().toLowerCase()); // get filename w/out extension
            createDirectory(fname); // creates directory from filename
        }
    }

    /** driving method  **/
    public static void parseDataToDB(){
        // initialize CSV Parser to read the csv and validate data
        CsvParser csvParser = new CsvParser(fname);
        csvParser.readCSV(file);
        csvParser.processVerifiedData();

        /* initialize SQLite DB, Table and insert valid data into DB */
        CsvSQLiteDao csvDao = new CsvSQLiteDao(fname);
        csvDao.createDB();
        csvDao.createTable(csvParser.getHeaders());
        csvDao.insert(csvParser.getDbCsv());

        /* writes files into directory */
        csvParser.writeFailedCsv();
        csvParser.writeLogFile();
        System.out.println("Files stored in directory: "+fname);
        System.out.println("----------");
    }
}

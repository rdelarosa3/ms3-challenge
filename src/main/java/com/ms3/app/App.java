package com.ms3.app;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);  // scanner to get user input
        System.out.println("Enter your CSV file path: ");
        String input = in.nextLine(); // gets users input in a string
        File file = new File(input); // gets file from input

        if (!file.getName().toLowerCase().endsWith(".csv")){    // verifies file is CSV extension
             System.err.println("Invalid File Type CSV Required");
             return;
        }

        String fname = FilenameUtils.removeExtension(file.getName().toLowerCase()); // get filename w/out extension
        createDirectory(fname); // creates directory from filename

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
    }

    /** creates a directory based on the file name to store db, logfile and csv **/
    public static void createDirectory(String fname){
        File directory = new File(fname);
        if(!directory.exists()) {
            System.out.println(directory.mkdir() ? "Creating Directory" : "Unable to create Directory");
        }else{
            System.out.println("Directory Already Exists");
        }
    }
}

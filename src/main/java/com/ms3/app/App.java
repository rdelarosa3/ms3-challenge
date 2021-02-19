package com.ms3.app;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your full file path: ");
        String input = in.nextLine();
        File file = new File(input);
        if (!file.getName().toLowerCase().endsWith(".csv")){
             System.err.println("Invalid File Type CSV Required");
             return;
        }
        String fname = FilenameUtils.removeExtension(file.getName().toLowerCase());
        createDirectory(fname);
        CsvParser csvParser = new CsvParser(file,fname);
        CsvSQLiteDao csvDao = new CsvSQLiteDao(fname);
        csvDao.createDB();
        csvDao.createTable(csvParser.getHeaders());
        csvDao.insert(csvParser.getDbCsv());
    }

    public static void createDirectory(String fname){
        File directory = new File(fname);
        if(!directory.exists()) {
            System.out.println(directory.mkdir() ? "Creating Directory" : "Unable to create Directory");
        }else{
            System.out.println("Directory Already Exists");
        }
    }
}

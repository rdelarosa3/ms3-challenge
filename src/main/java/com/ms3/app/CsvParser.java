package com.ms3.app;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvParser {
    
    private List<String[]> rCsv = new ArrayList<>(); // list of all rows in csv file
    private List<String[]> badCsv = new ArrayList<>();  // list of all rows failed verified data
    private List<CsvModel> dbCsv = new ArrayList<>(); // list of all rows passed as a model object verification
    private List<String> headers = new ArrayList<>();  // list of header row in csv
    private int received = 0;  // total received rows excluding header
    private int success = 0;  // total rows successfully verified
    private int failed = 0;  // total rows failed
    private String fname;

    public CsvParser(String fileName){
        fname = fileName;
    }

    /** reads CSV and creates a List of String[] from each row **/
    public void readCSV(File fileName){
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            System.out.println(ANSI_BLUE +"Reading CSV Data"+ANSI_RESET);
            setrCsv(reader.readAll());
            setReceived(rCsv.size()-1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /** verifies required fields are present and separates bad rows from good rows **/
    public void processVerifiedData(){
        System.out.println(ANSI_BLUE +"Processing Data"+ANSI_RESET);
        String[] header = Arrays.copyOfRange(rCsv.get(0),0,10); // get header row
        badCsv.add(header); // add header row to badCsv list
        setHeaders(new ArrayList<>(Arrays.asList(header))); // set headers to use in SQLite table creation
        rCsv.remove(0); // remove the header row from verification process
        /* verify if all fields are present in each row for columns 1 - 10*/
        for (String[] csvRow : rCsv ){
            List<String> requiredRow = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(csvRow, 0, 10)));
            if(requiredRow.contains("")) {
                badCsv.add(csvRow);  // if empty add to badCsv list
                setFailed();  //  updates Failed amount
            }else{
                setDataType(csvRow);
            }
        }
    }

    /** writes a CSV of all failed files **/
    public void writeFailedCsv(){
        try{
            CSVWriter writer = new CSVWriter(new FileWriter(fname+"/"+fname+"-bad.csv")); // creates file to write into directory
            writer.writeAll(badCsv);
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /** creates an Object model from row to validate data type and simplify sql insert stmnts.**/
    public void setDataType(String[] row){
        try{
            CsvModel m = new CsvModel(
                    row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9]
            );
            dbCsv.add(m); // adds verified row to process in SQLite
            setSuccess(); // updates Success amount
        } catch (Exception e) {
            badCsv.add(row); // adds failed to bad csv list
            setFailed();  // updates Failed amount
        }
    }

    /** creates log file based on data submitted **/
    public void writeLogFile(){
        System.out.println(ANSI_BLUE +"Writing log File"+ANSI_RESET);
        String outputString = String.format("%s records received %n%s records successful %n%s records failed",received,success,failed);  // string to write to log file
        try {
            FileWriter fw = new FileWriter(fname+"/"+fname+".log"); // create a new file with specified file name
            BufferedWriter bw = new BufferedWriter(fw);// create the IO stream on that file
            bw.write(outputString);   // writes a string into the IO stream
            bw.close();
            System.out.println(ANSI_BLUE+"Log file complete"+ANSI_RESET);
        }catch (IOException e){
            System.err.println("Failed to write log file");
            System.err.println(e.getMessage());
        }
    }

    /** GETTERS AND SETTERS **/

    public void setrCsv(List<String[]> rCsv) {
        this.rCsv = rCsv;
    }

    public List<String[]> getrCsv() {
        return rCsv;
    }

    public List<String[]> getBadCsv() {
        return badCsv;
    }

    public List<CsvModel> getDbCsv() {
        return dbCsv;
    }

    public int getReceived() {
        return received;
    }
    private void setReceived(int received) {
        this.received = received;
    }

    public int getSuccess() {
        return success;
    }
    public void setSuccess() {
        this.success ++;
    }

    public int getFailed() {
        return failed;
    }
    public void setFailed() {
        this.failed ++;
    }

    public List<String> getHeaders() {
        return headers;
    }
    private void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    /** colors for interface **/
    public static final String ANSI_BLUE = "\033[0;34m";    // BLUE
    public static final String ANSI_RESET = "\033[0m";  // Text Reset
}

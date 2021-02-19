package com.ms3.app;

public class App {
    public static void main(String[] args) {
        CsvParser csvParser = new CsvParser("ms3Interview.csv");
        System.out.println("Received: "+csvParser.getReceived());
        System.out.println("Success: "+csvParser.getSuccess());
        System.out.println("Failed: "+csvParser.getFailed());
    }
}

package com.ms3.app;

import java.sql.*;
import java.util.List;

public class Sqlite {
    private static Connection connection;
    private static String jdbcUrl;
    private static String filename = "ms3interview.db";
    private static String insertString = "";

    public static void main(String[] args) {
        String fname = "ms3Interview.csv";
        CsvParser csvParser = new CsvParser(fname);
        jdbcUrl = "jdbc:sqlite:" + filename.toLowerCase();
        createDB();
        createTable(csvParser.getHeaders());
        insert(csvParser.getDbCsv());
    }

    private static void connect() {
        try {
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createDB() {
        try {
            connection = DriverManager.getConnection(jdbcUrl);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("Using Driver Name : " + meta.getDriverName());
                System.out.println("Db has been created");
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            System.err.println(e.getMessage());
        }
    }

    private static void createTable(List<String> headers) {
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + "ms3interview" + " (");
        System.out.println("Creating table for DB");
        int index = 0;
        for (String col : headers) {
            switch (index) {
                case 4:
                    insertString+= (col+",");
                    sql.append(col).append(" text NOT NULL, ");
                    break;
                case 6:
                    insertString+= (col+",");
                    sql.append(col).append(" float NOT NULL, ");
                    break;
                case 7:
                case 8:
                    insertString+= (col+",");
                    sql.append(col).append(" boolean NOT NULL, ");
                    break;
                case 9:
                    insertString+= col;
                    sql.append(col).append(" varchar(255) NOT NULL);");
                    break;
                default:
                    insertString+= (col+",");
                    sql.append(col).append(" varchar(255) NOT NULL, ");
                    break;
            }
            index++;
        }
        try {
            Statement stmt = connection.createStatement();
            // create a new table
            stmt.execute(sql.toString());
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.err.println("Failed To Create Table");
            System.err.println(e.getMessage());
        }
    }

    private static void insert(List<CsvModel> dbData) {
        System.out.println("Inserting data into table");
        try {
            for (CsvModel c : dbData) {
                String sql = "INSERT INTO ms3interview (" + insertString + ") VALUES(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, c.getA());
                pstmt.setString(2, c.getB());
                pstmt.setString(3, c.getC());
                pstmt.setString(4, c.getD());
                pstmt.setString(5, c.getE());
                pstmt.setString(6, c.getE());
                pstmt.setDouble(7, c.getG());
                pstmt.setBoolean(8, c.isH());
                pstmt.setBoolean(9, c.isI());
                pstmt.setString(10, c.getJ());
                pstmt.executeUpdate();
            }
            System.out.println("Data insert complete");
        } catch (SQLException e) {
            System.err.println("Failed to insert data into table");
            System.err.println(e.getMessage());
        }
    }
}

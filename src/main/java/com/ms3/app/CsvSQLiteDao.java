package com.ms3.app;

import java.sql.*;
import java.util.List;

public class CsvSQLiteDao {
    private Connection connection; // use to set connection to sqlite
    private String jdbcUrl;   // url to use for connection
    private String insertString; // string to use in insert statement
    private String fname;  // filename to create db and table

    public CsvSQLiteDao(String fileName) {
        fname  = fileName; // gets the filename
        jdbcUrl = "jdbc:sqlite:"+ fname +"/"+ fname +".db";  // creates path for url
    }

    /** Creates connection to jdbc driver and creates new DB in specified directory based on the file name **/
    public void createDB() {
        try {
            connection = DriverManager.getConnection(jdbcUrl);  // create connection
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println(ANSI_BLUE+"Using Driver Name : " + meta.getDriverName() +ANSI_RESET);
                System.out.println(ANSI_BLUE+"Db has been created"+ANSI_RESET);
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            System.err.println(e.getMessage());
        }
    }

    /** creates table in db based on the file name and names columns from csv header **/
    public void createTable(List<String> headers) {
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + fname + " ("); // sql table stmnt
        System.out.println(ANSI_BLUE+"Creating table for DB"+ANSI_RESET);
        int index = 0;  // index value to use in switch
        insertString = ""; // create string to use in insert sql based on column names
        // creates table columns and data types from csv header list
        for (String col : headers) {
            switch (index) {
                case 4:
                    insertString += (col + ",");
                    sql.append(col).append(" text NOT NULL, ");
                    break;
                case 6:
                    insertString += (col + ",");
                    sql.append(col).append(" float NOT NULL, ");
                    break;
                case 7:
                case 8:
                    insertString += (col + ",");
                    sql.append(col).append(" boolean NOT NULL, ");
                    break;
                case 9:
                    insertString += col;
                    sql.append(col).append(" varchar(255) NOT NULL);");
                    break;
                default:
                    insertString += (col + ",");
                    sql.append(col).append(" varchar(255) NOT NULL, ");
                    break;
            }
            index++;
        }

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql.toString());
            System.out.println(ANSI_BLUE+"Table Created Successfully"+ANSI_RESET);
        } catch (SQLException e) {
            System.err.println("Failed To Create Table");
            System.err.println(e.getMessage());
        }
    }

    /** inserts values from the Obj model created from csv rows with valid data **/
    public void insert(List<CsvModel> dbData) {
        System.out.println(ANSI_BLUE+"Inserting data into table"+ANSI_RESET);
        int count = 3;
        try {
            // Loops through the Obj Models and sets each value by datatype into table.
            // using prepared stmnt to prevent sql injection
            for (CsvModel c : dbData) {
                if (count % 100 == 0) {
                    System.out.println();
                } else {
                    System.out.print(".");
                }
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
                count++;
            }
            System.out.println();
            System.out.println(ANSI_BLUE+"Data insert complete"+ANSI_RESET);
        } catch (SQLException e) {
            System.err.println("Failed to insert data into table");
            System.err.println(e.getMessage());

        }
    }

    /** colors for interface **/
    public static final String ANSI_BLUE = "\033[0;34m";    // BLUE
    public static final String ANSI_RESET = "\033[0m";  // Text Reset
}

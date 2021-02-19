package com.ms3.app;
/** CSV Model to collect row data in an object to simplify database insertion **/

public class CsvModel {
     private String a;
     private String b;
     private String c;
     private String d;
     private String e;
     private String f;
     private double g;
     private boolean h;
     private boolean i;
     private String j;

    // Create object from the row data
    public CsvModel(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = Double.parseDouble(g.replaceAll("[^\\d-.]", ""));
        this.h = Boolean.parseBoolean(h);
        this.i = Boolean.parseBoolean(i);
        this.j = j;
    }
    // Gets object fields to insert into db
    public CsvModel(String a, String b, String c, String d, String e, String f, double g, boolean h, boolean i, String j) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
    }

    // getters  & setters

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public boolean isH() {
        return h;
    }

    public void setH(boolean h) {
        this.h = h;
    }

    public boolean isI() {
        return i;
    }

    public void setI(boolean i) {
        this.i = i;
    }

    public String getJ() {
        return j;
    }

    public void setJ(String j) {
        this.j = j;
    }
}

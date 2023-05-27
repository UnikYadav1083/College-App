package com.example.collegeapp;

public class Ebookdata {
    private String name , pdfurl;

    public Ebookdata() {
    }

    public Ebookdata(String name, String pdfurl) {
        this.name = name;
        this.pdfurl = pdfurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPdfurl() {
        return pdfurl;
    }

    public void setPdfurl(String pdfurl) {
        this.pdfurl = pdfurl;
    }
}

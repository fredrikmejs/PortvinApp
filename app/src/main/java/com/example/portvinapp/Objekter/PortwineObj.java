package com.example.portvinapp.Objekter;


import android.util.Base64;

public class PortwineObj {

    String winery, type, wine_type;
    int vintage, bottle_year, grade, qty;
    String bitmap;
    String portImage;
    String notes;

    public PortwineObj(){

    }

    public PortwineObj(String bottle_year, String bitmap, String grade, String type, String vintage, String wine_type, String winery, String qty, String notes){
        this.winery = winery;
        this.vintage = Integer.parseInt(vintage);
        this.bottle_year = Integer.parseInt(bottle_year);
        this.grade = Integer.parseInt(grade);
        this.type = type;
        this.bitmap = bitmap;
        this.wine_type = wine_type;
        this.qty = Integer.parseInt(qty);
        this.notes = notes;

    }

    public String getWineType() {
        return wine_type;
    }

    public void setWineType(String wineType) {
        this.wine_type = wineType;
    }

    public String getWinery() {
        return winery;
    }

    public void setWinery(String winery) {
        this.winery = winery;
    }

    public int getVintage() {
        return vintage;
    }

    public void setVintage(int vintage) {
        this.vintage = vintage;
    }

    public int getBottleYear() {
        return bottle_year;
    }

    public void setBottleYear(int bottleYear) {
        this.bottle_year = bottleYear;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPortImage() {
        return portImage;
    }

    public void setPortImage(String portImage) {
        this.portImage = portImage;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

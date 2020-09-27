package com.example.portvinapp.Objekter;


import android.util.Base64;

public class PortwineObj {

    String winery, type, wine_type;
    int vintage, bottle_year, grade;
    String bitmap;
    String portImage;

    public PortwineObj(){

    }

    public PortwineObj(String bottle_year, String bitmap, String grade, String type, String vintage, String wine_type, String winery){
        this.winery = winery;
        this.vintage = Integer.parseInt(vintage);
        this.bottle_year = Integer.parseInt(bottle_year);
        this.grade = Integer.parseInt(grade);
        this.type = type;
        this.bitmap = bitmap;
        this.wine_type = wine_type;

    }

/*
    public PortwineObj(int bottle_year, int grade, String bitmap, String type, int vintage, String wine_type, String winery){
        this.winery = winery;
        this.vintage = vintage;
        this.bottle_year = bottle_year;
        this.grade = grade;
        this.type = type;
        this.wine_type = wine_type;
        this.portImage = bitmap;
    }

 */

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

}

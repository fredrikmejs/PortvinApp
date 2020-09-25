package com.example.portvinapp.Objekter;

import android.graphics.Bitmap;

public class PortvineObj {

    String winery, type, wineType;
    int vintage, bottleYear, grade;

    Bitmap portImage;

    public PortvineObj(Bitmap portImage, String winery, int vintage, int bottleYear, int grade, String type, String wineType){
        this.winery = winery;
        this.vintage = vintage;
        this.bottleYear = bottleYear;
        this.grade = grade;
        this.type = type;
        this.wineType = wineType;
        this.portImage = portImage;
    }

    public String getWineType() {
        return wineType;
    }

    public void setWineType(String wineType) {
        this.wineType = wineType;
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
        return bottleYear;
    }

    public void setBottleYear(int bottleYear) {
        this.bottleYear = bottleYear;
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

    public Bitmap getPortImage() {
        return portImage;
    }

    public void setPortImage(Bitmap portImage) {
        this.portImage = portImage;
    }
}

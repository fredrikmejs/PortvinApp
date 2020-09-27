package com.example.portvinapp.Domain.Singleton;

import android.graphics.Bitmap;
import android.widget.ProgressBar;

import com.example.portvinapp.Objekter.PortwineObj;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static final Singleton ourInstance = new Singleton();
    String wineType;
    int portType, position;
    List<PortwineObj> portWineArr = new ArrayList<>();
    Bitmap portBitmap;
    PortwineObj portwineObj;
    int sizeOfRecyler;
    String key, userID;

    private Singleton() {
    }

    //Creates instance for singleton
    public static Singleton getInstance() {
        return ourInstance;
    }

    public String getWineType() {
        return wineType;
    }

    public void setWineType(String type) {
        this.wineType = type;
    }

    public int getPortType() {
        return portType;
    }

    public void setPortType(int portType) {
        this.portType = portType;
    }

    public List<PortwineObj> getPortWineArr() {
        if (portWineArr.size() == 0){
          //add a way to get data
        }
        return portWineArr;
    }

    public void addPortwine(PortwineObj portwineObj){
        portWineArr.add(portwineObj);
    }

    public void setPortWineArr(List<PortwineObj> portWineArr) {
        this.portWineArr = portWineArr;
    }

    public void updatePortWineArr(PortwineObj obj){

        portWineArr.add(position,obj);
    }

    public Bitmap getPortBitmap() {
        return portBitmap;
    }

    public void setPortBitmap(Bitmap portBitmap) {
        this.portBitmap = portBitmap;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public PortwineObj getPortwineObj() {
        return portwineObj;
    }

    public void setPortwineObj(PortwineObj portwineObj) {
        this.portwineObj = portwineObj;
    }

    public int getSizeOfRecyler() {
        return sizeOfRecyler;
    }

    public void setSizeOfRecyler(int sizeOfRecyler) {
        this.sizeOfRecyler = sizeOfRecyler;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

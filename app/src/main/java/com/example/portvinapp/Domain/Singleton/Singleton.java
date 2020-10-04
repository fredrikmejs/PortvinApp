package com.example.portvinapp.Domain.Singleton;

import android.graphics.Bitmap;
import android.widget.ProgressBar;

import com.example.portvinapp.Objekter.PortwineObj;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static final Singleton ourInstance = new Singleton();

    int portType;
    List<PortwineObj> portWineArr = new ArrayList<>();
    List<String> keys = new ArrayList<>();
    Bitmap portBitmap;
    PortwineObj portwineObj;
    int sizeOfRecyler;
    String key;

    private Singleton() {
    }

    //Creates instance for singleton
    public static Singleton getInstance() {
        return ourInstance;
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

    public void setPortWineArr(List<PortwineObj> portWineArr) {
        this.portWineArr = portWineArr;
    }

    public void setPortBitmap(Bitmap portBitmap) {
        this.portBitmap = portBitmap;
    }

    public PortwineObj getPortwineObj() {
        return portwineObj;
    }

    public void setPortwineObj(PortwineObj portwineObj) {
        this.portwineObj = portwineObj;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public List<String> getKeys() {
        return keys;
    }
}

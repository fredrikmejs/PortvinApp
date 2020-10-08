package com.example.portvinapp.Domain.Singleton;

import android.graphics.Bitmap;

import com.example.portvinapp.Objekter.PortwineObj;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static final Singleton ourInstance = new Singleton();

    int portType;
    List<PortwineObj> portWineArr = new ArrayList<>();
    List<String> keys = new ArrayList<>();
    PortwineObj portwineObj;
    int sizeOfRecyler, position;
    String key;
    List<Integer> usedIndex = new ArrayList<>();

    private Singleton() {
    }

    //Creates instance for singleton
    public static Singleton getInstance() {
        return ourInstance;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public void setSizeOfRecyler(int sizeOfRecyler) {
        this.sizeOfRecyler = sizeOfRecyler;
    }

    public int getSizeOfRecyler() {
        return sizeOfRecyler;
    }

    public List<Integer> getUsedIndex() {
        return usedIndex;
    }

    public void addUsedIndex(int index) {
        this.usedIndex.add(index);
    }
    public void clearUsedIndexx(){
        usedIndex.clear();
    }
}

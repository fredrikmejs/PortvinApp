package com.example.portvinapp.Domain.Singleton;

import com.example.portvinapp.Objekter.PortvineObj;

import java.util.ArrayList;

public class Singleton {
    private static final Singleton ourInstance = new Singleton();
    String wineType;
    int portType;
    ArrayList<PortvineObj> portWineArr = new ArrayList<>();

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

    public ArrayList<PortvineObj> getPortWineArr() {
        if (portWineArr.size() == 0){
          //add a way to get data
        }
        return portWineArr;
    }

    public void addPortwine(PortvineObj portvineObj){
        portWineArr.add(portvineObj);
    }

    public void setPortWineArr(ArrayList<PortvineObj> portWineArr) {
        this.portWineArr = portWineArr;
    }
}

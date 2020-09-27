package com.example.portvinapp.Domain.Singleton;

import java.util.HashMap;
import java.util.Map;

public enum Portwine_enum {
    Tawny(0),
    White(1),
    Colheita(2),
    Vintage(3),
    LBV(4),
    age10(5),
    age20(6),
    age30(7),
    age40(8),
    Other(9);

    private  int value;

    Portwine_enum(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    private static final Map<Integer, Portwine_enum> typeByValue = new HashMap<>();

    static {
        for(Portwine_enum type : Portwine_enum.values()){
            typeByValue.put(type.value,type);
        }
    }

    public static Portwine_enum forValue(int value){
        return typeByValue.get(value);
    }

}

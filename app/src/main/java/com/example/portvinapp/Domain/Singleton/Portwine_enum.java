package com.example.portvinapp.Domain.Singleton;

import java.util.HashMap;
import java.util.Map;

public enum Portwine_enum {
    Tawny(0),
    White(1),
    Ruby(2),
    Colheita(3),
    Vintage(4),
    LBV(5),
    age10(6),
    age20(7),
    age30(8),
    age40(9),
    Other(10);

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

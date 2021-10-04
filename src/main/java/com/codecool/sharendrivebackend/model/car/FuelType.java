package com.codecool.sharendrivebackend.model.car;

public enum FuelType {

    GAS("GAS"),
    GASOLINE("GASOLINE"),
    DIESEL("DIESEL"),
    ELECTRIC("ELECTRIC");

    private final String name;

    FuelType(String name) {
        this.name = name;
    }

    public static FuelType getTypeByName(String searchedName){
        for(FuelType type : values()){
            if(type.name.equals(searchedName)){
                return type;
            }
        }
        return null;
    }
}

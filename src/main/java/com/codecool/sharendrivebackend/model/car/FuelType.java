package com.codecool.sharendrivebackend.model.car;

public enum FuelType {

    GAS("gas"),
    GASOLINE("gasoline"),
    DIESEL("diesel"),
    ELECTRIC("electric");

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

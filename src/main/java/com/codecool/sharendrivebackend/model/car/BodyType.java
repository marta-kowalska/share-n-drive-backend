package com.codecool.sharendrivebackend.model.car;

public enum BodyType {

    COUPE("coupe"),
    SEDAN("sedan"),
    STATION_WAGON("station_wagon"),
    HATCHBACK("hatchback"),
    CONVERTIBLE("convertible");

    private final String name;

    BodyType(String name) {
        this.name = name;
    }

    public static BodyType getTypeByName(String searchedName){
        for(BodyType type : values()){
            if(type.name.equals(searchedName)){
                return type;
            }
        }
        return null;
    }
}

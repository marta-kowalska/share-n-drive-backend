package com.codecool.sharendrivebackend.model.car;

public enum BodyType {

    COUPE("COUPE"),
    SEDAN("SEDAN"),
    SUV("SUV"),
    STATION_WAGON("STATION_WAGON"),
    HATCHBACK("HATCHBACK"),
    CONVERTIBLE("CONVERTIBLE");

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

    public String getName() {
        return name;
    }
}

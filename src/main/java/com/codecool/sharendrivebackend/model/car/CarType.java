package com.codecool.sharendrivebackend.model.car;

public enum CarType {

    HYBRID("hybrid"),
    SELF_DRIVING("self_driving"),
    ECO("eco"),
    GAS_GUZZLER("eco"),
    RACE_CAR("race_car"),
    FUN("fun"),
    LUXURY("luxury");

    private final String name;

    CarType(String name) {
        this.name = name;
    }

    public static CarType getTypeByName(String searchedName){
        for(CarType type : values()){
            if(type.name.equals(searchedName)){
                return type;
            }
        }
        return null;
    }
}

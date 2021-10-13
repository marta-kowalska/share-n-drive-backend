package com.codecool.sharendrivebackend.model.car;

public enum CarType {

    HYBRID("HYBRID"),
    SELF_DRIVING("SELF_DRIVING"),
    ECO("ECO"),
    GAS_GUZZLER("GAS_GUZZLER"),
    RACE_CAR("RACE_CAR"),
    FUN("FUN"),
    LUXURY("LUXURY"),
    MINI("MINI");

    private final String name;

    CarType(String name) {
        this.name = name;
    }

    public static CarType getTypeByName(String searchedName) {
        for (CarType type : values()) {
            if (type.name.equals(searchedName)) {
                return type;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}

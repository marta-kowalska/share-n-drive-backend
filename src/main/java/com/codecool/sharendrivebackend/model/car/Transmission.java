package com.codecool.sharendrivebackend.model.car;

public enum Transmission {
    MANUAL("MANUAL"),
    AUTOMATIC("AUTOMATIC");

    private final String name;

    Transmission(String name) {
        this.name = name;
    }

    public static Transmission getTypeByName(String searchedName) {
        for (Transmission type : values()) {
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


package com.finalproject.secondhand.enums;

public enum ECategory {
    Hobi("Hobi"),
    Kendaraan("Kendaraan"),
    Baju("Baju"),
    Elektronik("Elektronik"),
    Kesehatan("Kesehatan");

    private final String name;

    ECategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

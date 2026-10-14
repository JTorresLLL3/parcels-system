package com.garden.parcel.entity;

/**
 * Representa los tipos de suelo admitidos en los huertos urbanos:
 * arcilloso, limoso, arenoso.
 */
public enum SoilType {
    ARCILLOSO("arcilloso"),
    LIMOSO("limoso"),
    ARENOSO("arenoso");

    private final String value;

    SoilType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Parsea de manera flexible un String a SoilType (insensible a mayúsculas/minúsculas).
     */
    public static SoilType fromString(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null;
        }
        String normalized = text.trim();
        for (SoilType type : SoilType.values()) {
            if (type.name().equalsIgnoreCase(normalized) || type.value.equalsIgnoreCase(normalized)) {
                return type;
            }
        }
        return null;
    }
}

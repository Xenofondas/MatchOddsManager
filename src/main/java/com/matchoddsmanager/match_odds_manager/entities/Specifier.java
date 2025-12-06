package com.matchoddsmanager.match_odds_manager.entities;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Specifier {
    HOME_WIN("1"),
    DRAW("X"),
    AWAY_WIN("2");

    private final String value;

    Specifier(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static Specifier fromCode(String value) {
        for (Specifier specifier : values()) {
            if (specifier.value.equalsIgnoreCase(value)) {
                return specifier;
            }
        }

        throw new IllegalArgumentException("Unknown specifier: " + value);
    }
}

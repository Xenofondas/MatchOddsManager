package com.matchoddsmanager.match_odds_manager.entity;

public enum Sport {
    FOOTBALL(1),
    BASKETBALL(2);

    private final int sportType;

    Sport(int sportType) {
        this.sportType = sportType;
    }

    public int getSportType() {
        return sportType;
    }

    public static Sport fromCode(int sportType) {
        for (Sport sport : values()) {
            if (sport.getSportType() == sportType) {
                return sport;
            }
        }

        throw new IllegalArgumentException("Unknown Sport type: " + sportType);
    }
}

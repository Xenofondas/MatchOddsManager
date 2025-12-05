package com.matchoddsmanager.match_odds_manager.entities;

public enum Sport {
    FOOTBALL(1),
    BASKETBALL(2);

    private int sportType;

    Sport(int sportType) {
        this.sportType = sportType;
    }
}

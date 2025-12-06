package com.matchoddsmanager.match_odds_manager.entities.converter;

import com.matchoddsmanager.match_odds_manager.entities.Sport;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SportConverter implements AttributeConverter<Sport, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Sport sport) {
        if (sport == null) {
            return null;
        }
        return sport.getSportType();
    }

    @Override
    public Sport convertToEntityAttribute(Integer sportType) {
        if (sportType == null) {
            return null;
        }

        return Sport.fromCode(sportType);
    }
}

package com.matchoddsmanager.match_odds_manager.entities.converter;

import com.matchoddsmanager.match_odds_manager.entities.Specifier;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SpecifierConverter implements AttributeConverter<Specifier, String> {
    @Override
    public String convertToDatabaseColumn(Specifier specifier) {
        if (specifier == null) {
            return null;
        }

        return specifier.getValue();
    }

    @Override
    public Specifier convertToEntityAttribute(String specifier) {
        if (specifier == null) {
            return null;
        }

        return Specifier.fromCode(specifier);
    }
}

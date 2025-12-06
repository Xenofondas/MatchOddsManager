package com.matchoddsmanager.match_odds_manager.dto;

import com.matchoddsmanager.match_odds_manager.entity.Specifier;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchOddRequest {
    @NotNull(message = "Specifier (1, X, 2) is required")
    @Schema(
            type = "string",
            allowableValues = {"1", "X", "2"},
            example = "1",
            description = "The outcome specifier (1: Home Win, X: Draw, 2: Away Win)")
    @Column(name = "specifier")
    private Specifier specifier;

    @NotNull(message = "Odd value is required")
    @DecimalMin(value = "1.01", message = "Odd must be grater than 1.01")
    @DecimalMax(value = "1000.00", message = "Odd must be lower than 1000.00")
    private Double odd;
}

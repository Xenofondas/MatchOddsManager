package com.matchoddsmanager.match_odds_manager.dto;

import com.matchoddsmanager.match_odds_manager.entity.Sport;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchRequest {
    private String description;

    @NotNull(message = "Match date is required")
    private Date matchDate;

    @NotNull(message = "Match time is required")
    private LocalTime matchTime;

    @NotBlank(message = "Team A is required")
    private String teamA;

    @NotBlank(message = "Team B is required")
    private String teamB;

    @NotNull(message = "Sport is required")
    @Schema(
            type = "integer",
            allowableValues = {"FOOTBALL", "BASKETBALL"},
            example = "1",
            description = "The type of match (FOOTBALL:1 , BASKETBALL:2)")
    private Sport sport;

    @Valid
    private List<MatchOddRequest> matchOdds;
}

package com.matchoddsmanager.match_odds_manager.dto;

import com.matchoddsmanager.match_odds_manager.entities.MatchOdd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchRequest {
    private String description;
    private Date matchDate;
    private LocalTime matchTime;
    private String teamA;
    private String teamB;
    private String sport;
    private List<MatchOddRequest> matchOdds;
}

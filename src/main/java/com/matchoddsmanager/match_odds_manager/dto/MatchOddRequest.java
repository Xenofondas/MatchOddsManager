package com.matchoddsmanager.match_odds_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchOddRequest {
    private String specifier;
    private Double odd;
}

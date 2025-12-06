package com.matchoddsmanager.match_odds_manager.dto;

import com.matchoddsmanager.match_odds_manager.entity.Specifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchOddUpdateRequest {
    private Specifier specifier;
    private Double odd;
}

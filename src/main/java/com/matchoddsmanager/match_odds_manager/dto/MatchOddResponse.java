package com.matchoddsmanager.match_odds_manager.dto;

import com.matchoddsmanager.match_odds_manager.entities.Specifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchOddResponse {
    private Long matchOddId;
    private Specifier specifier;
    private Double odd;
}

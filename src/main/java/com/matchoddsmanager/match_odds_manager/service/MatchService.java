package com.matchoddsmanager.match_odds_manager.service;

import com.matchoddsmanager.match_odds_manager.dto.MatchRequest;
import com.matchoddsmanager.match_odds_manager.dto.MatchResponse;
import com.matchoddsmanager.match_odds_manager.entities.Match;

import java.util.List;

public interface MatchService {
    MatchResponse createMatch(MatchRequest matchRequest);
    List<MatchResponse> getAllMatches();
    MatchResponse getMatchById(Long id);
    MatchResponse updateMatch(Long id, MatchRequest updatedMatch);
    void deleteMatch(Long id);
}

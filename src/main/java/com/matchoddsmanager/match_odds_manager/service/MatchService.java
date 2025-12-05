package com.matchoddsmanager.match_odds_manager.service;

import com.matchoddsmanager.match_odds_manager.dto.MatchRequest;
import com.matchoddsmanager.match_odds_manager.dto.MatchResponse;
import com.matchoddsmanager.match_odds_manager.entities.Match;

import java.util.List;

public interface MatchService {
    MatchResponse createMatch(MatchRequest matchRequest);
    List<Match> getAllMatches();
    Match getMatchById(Long id);
    Match updateMatch(Long id, Match updatedMatch);
    void deleteMatch(Long id);
}

package com.matchoddsmanager.match_odds_manager.service;

import com.matchoddsmanager.match_odds_manager.dto.*;

import java.util.List;

public interface MatchService {
    MatchResponse createMatch(MatchRequest matchRequest);

    List<MatchResponse> getAllMatches();

    MatchResponse getMatchByMatchId(Long matchId);

    MatchResponse updateMatch(Long id, MatchRequest matchRequest);

    MatchOddResponse updateMatchOdd(Long matchOddId, MatchOddRequest matchOddRequest);

    MatchResponse updateMatchOddBySpecifier(Long matchOddId, MatchOddUpdateRequest matchOddUpdateRequest);

    void deleteMatch(Long id);
}

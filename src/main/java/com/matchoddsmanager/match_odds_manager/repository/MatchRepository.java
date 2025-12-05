package com.matchoddsmanager.match_odds_manager.repository;

import com.matchoddsmanager.match_odds_manager.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Match findByMatchId(Long matchId);

    List<Match> findAllBySport(Integer sport);
}

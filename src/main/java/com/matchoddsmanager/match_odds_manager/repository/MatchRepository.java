package com.matchoddsmanager.match_odds_manager.repository;

import com.matchoddsmanager.match_odds_manager.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByMatchId(Long matchId);

    List<Match> findAllBySport(Integer sport);
}

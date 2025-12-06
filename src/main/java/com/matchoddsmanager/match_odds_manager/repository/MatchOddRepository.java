package com.matchoddsmanager.match_odds_manager.repository;

import com.matchoddsmanager.match_odds_manager.entities.Match;
import com.matchoddsmanager.match_odds_manager.entities.MatchOdd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchOddRepository extends JpaRepository<MatchOdd, Long> {
    MatchOdd findByMatchOddId(Long matchOddId);
}

package com.matchoddsmanager.match_odds_manager.repository;

import com.matchoddsmanager.match_odds_manager.entity.MatchOdd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchOddRepository extends JpaRepository<MatchOdd, Long> {
    Optional<MatchOdd> findByMatchOddId(Long matchOddId);
}

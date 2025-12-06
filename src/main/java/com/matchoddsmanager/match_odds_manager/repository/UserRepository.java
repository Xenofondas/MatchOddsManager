package com.matchoddsmanager.match_odds_manager.repository;

import com.matchoddsmanager.match_odds_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}

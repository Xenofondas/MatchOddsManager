package com.matchoddsmanager.match_odds_manager.repositorie;

import com.matchoddsmanager.match_odds_manager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}

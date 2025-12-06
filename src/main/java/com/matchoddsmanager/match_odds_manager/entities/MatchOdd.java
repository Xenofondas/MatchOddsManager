package com.matchoddsmanager.match_odds_manager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MatchOdd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_odd_id")
    private Long matchOddId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @Column(name = "specifier")
    private Specifier specifier;

    @Column(name = "odd")
    private Double odd;

    public MatchOdd(Long matchOddId) {
        this.matchOddId = matchOddId;
    }
}

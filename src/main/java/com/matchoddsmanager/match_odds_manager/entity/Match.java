package com.matchoddsmanager.match_odds_manager.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "description")
    private String description;

    @NotNull(message = "Match date is required")
    @Column(name = "match_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date matchDate;

    @NotNull(message = "Match time is required")
    @Column(name = "match_time")
    private LocalTime matchTime;

    @NotBlank(message = "Team A is required")
    @Column(name = "team_a")
    private String teamA;

    @NotBlank(message = "Team B is required")
    @Column(name = "team_b")
    private String teamB;

    @NotNull(message = "Sport is required")
    @Column(name = "sport")
    private Sport sport;

    @Valid
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MatchOdd> odds = new HashSet<>();

    public Match(Long matchId) {
        this.matchId = matchId;
    }
}

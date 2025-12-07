package com.matchoddsmanager.match_odds_manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
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

    @NotNull(message = "Specifier (1, X, 2) is required")
    @Column(name = "specifier")
    private Specifier specifier;

    @NotNull(message = "Odd value is required")
    @DecimalMin(value = "1.01", message = "Odd must be grater than 1.01")
    @DecimalMax(value = "1000.00", message = "Odd must be lower than 1000.00")
    @Column(name = "odd")
    private Double odd;

    public MatchOdd(Long matchOddId) {
        this.matchOddId = matchOddId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (!(o instanceof MatchOdd)) {
            return false;
        }

        MatchOdd other = (MatchOdd) o;

        return matchOddId != null && matchOddId.equals(other.getMatchOddId());
    }

    @Override
    public int hashCode() {
        return matchOddId != null ? matchOddId.hashCode() : getClass().hashCode();
    }

}

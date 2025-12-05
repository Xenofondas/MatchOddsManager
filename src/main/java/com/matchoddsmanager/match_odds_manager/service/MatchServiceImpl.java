package com.matchoddsmanager.match_odds_manager.service;

import com.matchoddsmanager.match_odds_manager.dto.MatchOddRequest;
import com.matchoddsmanager.match_odds_manager.dto.MatchOddResponse;
import com.matchoddsmanager.match_odds_manager.dto.MatchRequest;
import com.matchoddsmanager.match_odds_manager.dto.MatchResponse;
import com.matchoddsmanager.match_odds_manager.entities.Match;
import com.matchoddsmanager.match_odds_manager.entities.MatchOdd;
import com.matchoddsmanager.match_odds_manager.entities.Sport;
import com.matchoddsmanager.match_odds_manager.repository.MatchRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    @Transactional
    public MatchResponse createMatch(MatchRequest matchRequest) {
        Match match = new Match();
        match.setDescription(matchRequest.getDescription());
        match.setMatchDate(matchRequest.getMatchDate());
        match.setMatchTime(matchRequest.getMatchTime());
        match.setTeamA(matchRequest.getTeamA());
        match.setTeamB(matchRequest.getTeamB());
        match.setSport(Sport.valueOf(matchRequest.getSport()));

        if (matchRequest.getMatchOdds() != null && !matchRequest.getMatchOdds().isEmpty()) {
            matchRequest.getMatchOdds().forEach(md -> {
                MatchOdd matchOdd = new MatchOdd();
                matchOdd.setMatch(match);
                matchOdd.setSpecifier(md.getSpecifier());
                matchOdd.setOdd(md.getOdd());

                match.getOdds().add(matchOdd);
            });
        }

        return getMatchResponse(matchRepository.save(match));
    }

    private MatchResponse getMatchResponse(Match match) {
        MatchResponse response = new MatchResponse();
        response.setMatchId(match.getMatchId());
        response.setDescription(match.getDescription());
        response.setMatchDate(match.getMatchDate());
        response.setMatchTime(match.getMatchTime());
        response.setTeamA(match.getTeamA());
        response.setTeamB(match.getTeamB());
        response.setSport(match.getSport().name());

        response.setMatchOdds(
                match.getOdds().stream()
                        .map(o -> {
                            MatchOddResponse r = new MatchOddResponse();
                            r.setMatchOddId(o.getMatchOddId());
                            r.setSpecifier(o.getSpecifier());
                            r.setOdd(o.getOdd());
                            return r;
                        })
                        .toList()
        );

        return response;
    }

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found with id " + id));
    }

    @Override
    public Match updateMatch(Long id, Match updatedMatch) {
        Match match = getMatchById(id);

        match.setDescription(updatedMatch.getDescription());
        match.setMatchDate(updatedMatch.getMatchDate());
        match.setMatchTime(updatedMatch.getMatchTime());
        match.setTeamA(updatedMatch.getTeamA());
        match.setTeamB(updatedMatch.getTeamB());
        match.setSport(updatedMatch.getSport());

        return matchRepository.save(match);
    }

    @Override
    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}

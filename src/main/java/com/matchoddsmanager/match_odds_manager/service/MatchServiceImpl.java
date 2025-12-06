package com.matchoddsmanager.match_odds_manager.service;

import com.matchoddsmanager.match_odds_manager.dto.*;
import com.matchoddsmanager.match_odds_manager.entity.Match;
import com.matchoddsmanager.match_odds_manager.entity.MatchOdd;
import com.matchoddsmanager.match_odds_manager.entity.Sport;
import com.matchoddsmanager.match_odds_manager.repository.MatchOddRepository;
import com.matchoddsmanager.match_odds_manager.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchOddRepository matchOddRepository;

    @Override
    public MatchResponse createMatch(MatchRequest matchRequest) {
        Match match = new Match();
        match.setDescription(matchRequest.getDescription());
        match.setMatchDate(matchRequest.getMatchDate());
        match.setMatchTime(matchRequest.getMatchTime());
        match.setTeamA(matchRequest.getTeamA());
        match.setTeamB(matchRequest.getTeamB());
        match.setSport(matchRequest.getSport());

        if (matchRequest.getMatchOdds() != null && !matchRequest.getMatchOdds().isEmpty()) {
            matchRequest.getMatchOdds().stream()
                    .map(mo -> {
                        MatchOdd matchOdd = new MatchOdd();
                        matchOdd.setMatch(match);
                        matchOdd.setSpecifier(mo.getSpecifier());
                        matchOdd.setOdd(mo.getOdd());

                        return matchOdd;
                    }).forEach(mo -> match.getOdds().add(mo));
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
    public List<MatchResponse> getAllMatches() {
        List<Match> savedMatches = matchRepository.findAll();

        return savedMatches.stream()
                .map(m -> getMatchResponse(m))
                .collect(Collectors.toList());
    }

    @Override
    public MatchResponse getMatchByMatchId(Long matchId) {
        Optional<Match> optionalMatch = matchRepository.findByMatchId(matchId);
        if (optionalMatch.isEmpty()) {
            throw new RuntimeException("Match not found with id " + matchId);
        }

        return getMatchResponse(optionalMatch.get());
    }

    @Override
    public MatchResponse updateMatch(Long matchId, MatchRequest updatedMatch) {
        Optional<Match> match = matchRepository.findByMatchId(matchId);
        if (match.isEmpty()) {
            throw new RuntimeException("Match not found with id " + matchId);
        } else {
            Match savedMatch = match.get();
            savedMatch.setDescription(updatedMatch.getDescription());
            savedMatch.setMatchDate(updatedMatch.getMatchDate());
            savedMatch.setMatchTime(updatedMatch.getMatchTime());
            savedMatch.setTeamA(updatedMatch.getTeamA());
            savedMatch.setTeamB(updatedMatch.getTeamB());
            savedMatch.setSport(updatedMatch.getSport());

            if (updatedMatch.getMatchOdds() != null && !updatedMatch.getMatchOdds().isEmpty()) {
                updatedMatch.getMatchOdds().forEach(o -> {
                    MatchOdd savedOdd = savedMatch.getOdds().stream()
                            .filter(odd -> odd.getSpecifier().equals(o.getSpecifier()))
                            .findFirst()
                            .orElse(null);
                    if (savedOdd != null) {
                        savedOdd.setOdd(o.getOdd());
                    } else {
                        MatchOdd matchOdd = new MatchOdd();
                        matchOdd.setMatch(savedMatch);
                        matchOdd.setSpecifier(o.getSpecifier());
                        matchOdd.setOdd(o.getOdd());

                        savedMatch.getOdds().add(matchOdd);
                    }
                });
            }

            return getMatchResponse(matchRepository.save(savedMatch));
        }
    }

    @Override
    public MatchOddResponse updateMatchOdd(Long matchOddId, MatchOddRequest matchOddRequest) {
        Optional<MatchOdd> matchOddOptional = matchOddRepository.findByMatchOddId(matchOddId);
        if (matchOddOptional.isEmpty()) {
            throw new RuntimeException("MatchOdd not found with id " + matchOddId);
        }

        MatchOdd matchOdd = matchOddOptional.get();
        matchOdd.setOdd(matchOddRequest.getOdd());

        matchOddRepository.save(matchOdd);

        return getMatchOddResponse(matchOdd);
    }

    @Override
    public MatchResponse updateMatchOddBySpecifier(Long matchId, MatchOddUpdateRequest matchOddUpdateRequest) {
        Optional<Match> optionalMatch = matchRepository.findByMatchId(matchId);
        if (optionalMatch.isEmpty()) {
            throw new RuntimeException("MatchOdd not found with id" + matchId + " and specifier " + matchOddUpdateRequest.getSpecifier());
        }

        Match match = optionalMatch.get();
        Optional<MatchOdd> existingMatchOdd = match.getOdds().stream()
                .filter(o -> o.getSpecifier().equals(matchOddUpdateRequest.getSpecifier()))
                .findFirst();
        if (existingMatchOdd.isPresent()) {
            existingMatchOdd.get().setOdd(matchOddUpdateRequest.getOdd());
        } else {
            MatchOdd matchOdd = new MatchOdd();
            matchOdd.setMatch(match);
            matchOdd.setSpecifier(matchOddUpdateRequest.getSpecifier());
            matchOdd.setOdd(matchOddUpdateRequest.getOdd());

            match.getOdds().add(matchOdd);
        }

        return getMatchResponse(matchRepository.save(match));
    }

    private MatchOddResponse getMatchOddResponse(MatchOdd matchOdd) {
        MatchOddResponse matchOddResponse = new MatchOddResponse();
        matchOddResponse.setMatchOddId(matchOdd.getMatchOddId());
        matchOddResponse.setSpecifier(matchOdd.getSpecifier());
        matchOddResponse.setOdd(matchOdd.getOdd());

        return matchOddResponse;
    }

    @Override
    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}

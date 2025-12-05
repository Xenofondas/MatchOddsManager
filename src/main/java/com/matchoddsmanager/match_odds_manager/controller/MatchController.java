package com.matchoddsmanager.match_odds_manager.controller;

import com.matchoddsmanager.match_odds_manager.dto.ApiResponse;
import com.matchoddsmanager.match_odds_manager.dto.MatchRequest;
import com.matchoddsmanager.match_odds_manager.dto.MatchResponse;
import com.matchoddsmanager.match_odds_manager.entities.Match;
import com.matchoddsmanager.match_odds_manager.entities.User;
import com.matchoddsmanager.match_odds_manager.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping
    public ResponseEntity<ApiResponse<MatchResponse>> createMatch(@RequestBody MatchRequest matchRequest) {
        MatchResponse savedMatch = matchService.createMatch(matchRequest);
        ApiResponse<MatchResponse> apiResponse = ApiResponse.<MatchResponse>builder()
                .status("SUCCESS")
                .message("Match created successfully")
                .data(savedMatch)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Match>>> getAllMatches() {
        List<Match> matches = matchService.getAllMatches();
        ApiResponse<List<Match>> apiResponse = ApiResponse.<List<Match>>builder()
                .status("SUCCESS")
                .message("Matches retrieved successfully")
                .data(matches)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Match>> getMatchById(@PathVariable Long id) {
        Match match = matchService.getMatchById(id);

        ApiResponse<Match> apiResponse = ApiResponse.<Match>builder()
                .status("SUCCESS")
                .message("Match retrieved successfully")
                .data(match)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Match>> updateMatch(@PathVariable Long id, @RequestBody Match updatedMatch) {
        Match match = matchService.updateMatch(id, updatedMatch);

        ApiResponse<Match> apiResponse = ApiResponse.<Match>builder()
                .status("SUCCESS")
                .message("Match updated successfully")
                .data(match)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}

package com.matchoddsmanager.match_odds_manager.controller;

import com.matchoddsmanager.match_odds_manager.dto.*;
import com.matchoddsmanager.match_odds_manager.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<List<MatchResponse>>> getAllMatches() {
        List<MatchResponse> matches = matchService.getAllMatches();
        ApiResponse<List<MatchResponse>> apiResponse = ApiResponse.<List<MatchResponse>>builder()
                .status("SUCCESS")
                .message("Matches retrieved successfully")
                .data(matches)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MatchResponse>> getMatchById(@PathVariable Long id) {
        MatchResponse match = matchService.getMatchByMatchId(id);

        ApiResponse<MatchResponse> apiResponse = ApiResponse.<MatchResponse>builder()
                .status("SUCCESS")
                .message("Match retrieved successfully")
                .data(match)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MatchResponse>> updateMatch(@PathVariable Long id, @RequestBody MatchRequest updatedMatch) {
        MatchResponse match = matchService.updateMatch(id, updatedMatch);

        ApiResponse<MatchResponse> apiResponse = ApiResponse.<MatchResponse>builder()
                .status("SUCCESS")
                .message("Match updated successfully")
                .data(match)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/matchOdd/{id}")
    public ResponseEntity<ApiResponse<MatchOddResponse>> updateMatchOdd(@PathVariable Long id, @RequestBody MatchOddRequest matchOddRequest) {
        MatchOddResponse matchOddResponse = matchService.updateMatchOdd(id, matchOddRequest);

        ApiResponse<MatchOddResponse> apiResponse = ApiResponse.<MatchOddResponse>builder()
                .status("SUCCESS")
                .message("MatchOdd updated successfully")
                .data(matchOddResponse)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{matchId}/odds")
    public ResponseEntity<ApiResponse<MatchResponse>> updateMatchOddΒySpecifier(@PathVariable Long matchId, @RequestBody MatchOddUpdateRequest matchOddUpdateRequest) {
        MatchResponse matchResponse = matchService.updateMatchOddBySpecifier(matchId, matchOddUpdateRequest);

        ApiResponse<MatchResponse> apiResponse = ApiResponse.<MatchResponse>builder()
                .status("SUCCESS")
                .message("MatchOdd updated successfully")
                .data(matchResponse)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.team.fch.secretofmay.domain.puzzle.controller;

import com.example.team.fch.secretofmay.domain.puzzle.dto.request.SubmitPuzzleRequest;
import com.example.team.fch.secretofmay.domain.puzzle.dto.response.StartRandomPuzzleResponse;
import com.example.team.fch.secretofmay.domain.puzzle.dto.response.SubmitPuzzleResponse;
import com.example.team.fch.secretofmay.domain.puzzle.service.StartRandomPuzzleService;
import com.example.team.fch.secretofmay.domain.puzzle.service.SubmitPuzzleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/puzzle")
public class PuzzleController {

    private final StartRandomPuzzleService startRandomPuzzleService;
    private final SubmitPuzzleService submitPuzzleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/random/start")
    public StartRandomPuzzleResponse startRandomPuzzle(
            @RequestHeader(value = "X-Session-Id", required = false) String sessionId
    ) {
        return startRandomPuzzleService.execute(sessionId);
    }

    @PostMapping("/submit/{attemptId}")
    public SubmitPuzzleResponse submitPuzzle(
            @RequestHeader(value = "X-Session-Id", required = false) String sessionId,
            @PathVariable Long attemptId,
            @Valid @RequestBody SubmitPuzzleRequest request
    ) {
        return submitPuzzleService.execute(sessionId, attemptId, request);
    }
}
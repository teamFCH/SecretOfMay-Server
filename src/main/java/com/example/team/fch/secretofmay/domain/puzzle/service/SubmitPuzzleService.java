package com.example.team.fch.secretofmay.domain.puzzle.service;

import com.example.team.fch.secretofmay.domain.puzzle.dto.request.SubmitPuzzleRequest;
import com.example.team.fch.secretofmay.domain.puzzle.dto.response.SubmitPuzzleResponse;

public interface SubmitPuzzleService {

    SubmitPuzzleResponse execute(String sessionId, Long attemptId, SubmitPuzzleRequest request);
}
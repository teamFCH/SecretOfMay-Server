package com.example.team.fch.secretofmay.domain.puzzle.service;

import com.example.team.fch.secretofmay.domain.puzzle.dto.response.StartRandomPuzzleResponse;

public interface StartRandomPuzzleService {

    StartRandomPuzzleResponse execute(String sessionId);
}
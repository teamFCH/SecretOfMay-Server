package com.example.team.fch.secretofmay.domain.puzzle.dto.response;

import java.time.LocalDateTime;

public record SubmitPuzzleResponse(
        boolean completed,
        Long puzzleId,
        Long durationMs,
        String formattedDuration,
        Long rank,
        LocalDateTime submittedAt
) {
}
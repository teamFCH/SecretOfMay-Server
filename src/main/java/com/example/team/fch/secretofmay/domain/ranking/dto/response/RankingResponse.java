package com.example.team.fch.secretofmay.domain.ranking.dto.response;

import java.time.LocalDateTime;

public record RankingResponse(
        Long rank,
        String nickname,
        String puzzleTitle,
        Long durationMs,
        String formattedDuration,
        LocalDateTime submittedAt
) {
}
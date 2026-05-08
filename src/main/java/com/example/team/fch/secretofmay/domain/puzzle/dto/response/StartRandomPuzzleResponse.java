package com.example.team.fch.secretofmay.domain.puzzle.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record StartRandomPuzzleResponse(
        Long attemptId,
        Long puzzleId,
        String title,
        int rowSize,
        int colSize,
        LocalDateTime startedAt,
        List<PuzzleCellResponse> cells,
        List<PuzzleClueResponse> clues
) {
}
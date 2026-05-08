package com.example.team.fch.secretofmay.domain.puzzle.dto.response;

public record PuzzleCellResponse(
        int rowIndex,
        int colIndex,
        boolean isBlock
) {
}
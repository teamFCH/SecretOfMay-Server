package com.example.team.fch.secretofmay.domain.puzzle.dto.response;

import com.example.team.fch.secretofmay.domain.puzzle.entity.Direction;

public record PuzzleClueResponse(
        int clueNumber,
        Direction direction,
        String content,
        int startRowIndex,
        int startColIndex,
        int length
) {
}
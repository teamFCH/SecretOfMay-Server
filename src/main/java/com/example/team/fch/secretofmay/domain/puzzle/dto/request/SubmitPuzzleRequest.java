package com.example.team.fch.secretofmay.domain.puzzle.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record SubmitPuzzleRequest(
        @Valid
        @NotEmpty(message = "제출할 칸 정보는 필수입니다.")
        List<SubmitPuzzleCellRequest> cells
) {
}
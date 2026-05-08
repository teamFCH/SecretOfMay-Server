package com.example.team.fch.secretofmay.domain.puzzle.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubmitPuzzleCellRequest(
        @NotNull(message = "행 인덱스는 필수입니다.")
        Integer rowIndex,

        @NotNull(message = "열 인덱스는 필수입니다.")
        Integer colIndex,

        @NotBlank(message = "입력값은 필수입니다.")
        String value
) {
}
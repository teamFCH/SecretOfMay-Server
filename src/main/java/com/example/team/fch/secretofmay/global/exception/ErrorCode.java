package com.example.team.fch.secretofmay.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "INVALID_INPUT_VALUE", "잘못된 입력값입니다."),
    SESSION_NOT_FOUND(HttpStatus.UNAUTHORIZED, "SESSION_NOT_FOUND", "세션을 찾을 수 없습니다."),
    PUZZLE_NOT_FOUND(HttpStatus.NOT_FOUND, "PUZZLE_NOT_FOUND", "퍼즐을 찾을 수 없습니다."),
    ATTEMPT_NOT_FOUND(HttpStatus.NOT_FOUND, "ATTEMPT_NOT_FOUND", "풀이 기록을 찾을 수 없습니다."),
    WRONG_ANSWER(HttpStatus.BAD_REQUEST, "WRONG_ANSWER", "틀린 문제가 있습니다."),
    FORBIDDEN_ATTEMPT(HttpStatus.FORBIDDEN, "FORBIDDEN_ATTEMPT", "다른 사용자의 풀이 기록입니다."),
    ALREADY_COMPLETED(HttpStatus.CONFLICT, "ALREADY_COMPLETED", "이미 완료된 퍼즐입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 내부 오류입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
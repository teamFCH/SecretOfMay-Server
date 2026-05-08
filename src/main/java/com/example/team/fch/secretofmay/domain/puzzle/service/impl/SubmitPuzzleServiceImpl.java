package com.example.team.fch.secretofmay.domain.puzzle.service.impl;

import com.example.team.fch.secretofmay.domain.attempt.entity.PuzzleAttempt;
import com.example.team.fch.secretofmay.domain.attempt.repository.PuzzleAttemptRepository;
import com.example.team.fch.secretofmay.domain.guest.entity.Guest;
import com.example.team.fch.secretofmay.domain.guest.service.GetGuestService;
import com.example.team.fch.secretofmay.domain.puzzle.dto.request.SubmitPuzzleRequest;
import com.example.team.fch.secretofmay.domain.puzzle.dto.response.SubmitPuzzleResponse;
import com.example.team.fch.secretofmay.domain.puzzle.entity.Direction;
import com.example.team.fch.secretofmay.domain.puzzle.entity.PuzzleClue;
import com.example.team.fch.secretofmay.domain.puzzle.repository.PuzzleClueRepository;
import com.example.team.fch.secretofmay.domain.puzzle.service.SubmitPuzzleService;
import com.example.team.fch.secretofmay.global.exception.ErrorCode;
import com.example.team.fch.secretofmay.global.exception.GlobalException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubmitPuzzleServiceImpl implements SubmitPuzzleService {

    private final GetGuestService getGuestService;
    private final PuzzleAttemptRepository puzzleAttemptRepository;
    private final PuzzleClueRepository puzzleClueRepository;

    @Override
    @Transactional
    public SubmitPuzzleResponse execute(String sessionId, Long attemptId, SubmitPuzzleRequest request) {
        Guest guest = getGuestService.getBySessionId(sessionId);

        PuzzleAttempt attempt = puzzleAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new GlobalException(ErrorCode.ATTEMPT_NOT_FOUND));

        if (!attempt.getGuest().getId().equals(guest.getId())) {
            throw new GlobalException(ErrorCode.FORBIDDEN_ATTEMPT);
        }

        if (attempt.isCompleted()) {
            throw new GlobalException(ErrorCode.ALREADY_COMPLETED);
        }

        Map<String, String> submittedCells = request.cells()
                .stream()
                .collect(Collectors.toMap(
                        cell -> key(cell.rowIndex(), cell.colIndex()),
                        cell -> cell.value().trim(),
                        (oldValue, newValue) -> newValue
                ));

        List<PuzzleClue> clues = puzzleClueRepository.findAllByPuzzle(attempt.getPuzzle());

        boolean hasWrongAnswer = clues.stream()
                .anyMatch(clue -> isWrongAnswer(clue, submittedCells));

        if (hasWrongAnswer) {
            throw new GlobalException(ErrorCode.WRONG_ANSWER);
        }

        LocalDateTime submittedAt = LocalDateTime.now();
        attempt.complete(submittedAt);

        Long rank = puzzleAttemptRepository.countByIsCompletedTrueAndDurationMsLessThan(attempt.getDurationMs()) + 1;

        return new SubmitPuzzleResponse(
                true,
                attempt.getPuzzle().getId(),
                attempt.getDurationMs(),
                formatDuration(attempt.getDurationMs()),
                rank,
                attempt.getSubmittedAt()
        );
    }

    private boolean isWrongAnswer(PuzzleClue clue, Map<String, String> submittedCells) {
        String answer = clue.getAnswer();

        for (int i = 0; i < answer.length(); i++) {
            int rowIndex = clue.getStartRowIndex() + (clue.getDirection() == Direction.DOWN ? i : 0);
            int colIndex = clue.getStartColIndex() + (clue.getDirection() == Direction.ACROSS ? i : 0);

            String submittedValue = submittedCells.get(key(rowIndex, colIndex));

            if (submittedValue == null || submittedValue.isBlank()) {
                return true;
            }

            if (!String.valueOf(answer.charAt(i)).equals(submittedValue)) {
                return true;
            }
        }

        return false;
    }

    private String key(int rowIndex, int colIndex) {
        return rowIndex + ":" + colIndex;
    }

    private String formatDuration(long durationMs) {
        long minutes = durationMs / 60000;
        long seconds = (durationMs % 60000) / 1000;
        long hundredths = (durationMs % 1000) / 10;

        return String.format("%02d:%02d.%02d", minutes, seconds, hundredths);
    }
}
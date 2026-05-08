package com.example.team.fch.secretofmay.domain.puzzle.service.impl;

import com.example.team.fch.secretofmay.domain.attempt.entity.PuzzleAttempt;
import com.example.team.fch.secretofmay.domain.attempt.repository.PuzzleAttemptRepository;
import com.example.team.fch.secretofmay.domain.guest.entity.Guest;
import com.example.team.fch.secretofmay.domain.guest.service.GetGuestService;
import com.example.team.fch.secretofmay.domain.puzzle.dto.response.PuzzleCellResponse;
import com.example.team.fch.secretofmay.domain.puzzle.dto.response.PuzzleClueResponse;
import com.example.team.fch.secretofmay.domain.puzzle.dto.response.StartRandomPuzzleResponse;
import com.example.team.fch.secretofmay.domain.puzzle.entity.Puzzle;
import com.example.team.fch.secretofmay.domain.puzzle.repository.PuzzleCellRepository;
import com.example.team.fch.secretofmay.domain.puzzle.repository.PuzzleClueRepository;
import com.example.team.fch.secretofmay.domain.puzzle.repository.PuzzleRepository;
import com.example.team.fch.secretofmay.domain.puzzle.service.StartRandomPuzzleService;
import com.example.team.fch.secretofmay.global.exception.ErrorCode;
import com.example.team.fch.secretofmay.global.exception.GlobalException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StartRandomPuzzleServiceImpl implements StartRandomPuzzleService {

    private final GetGuestService getGuestService;
    private final PuzzleRepository puzzleRepository;
    private final PuzzleCellRepository puzzleCellRepository;
    private final PuzzleClueRepository puzzleClueRepository;
    private final PuzzleAttemptRepository puzzleAttemptRepository;

    @Override
    @Transactional
    public StartRandomPuzzleResponse execute(String sessionId) {
        Guest guest = getGuestService.getBySessionId(sessionId);

        List<Puzzle> puzzles = puzzleRepository.findAllByIsActiveTrue();

        if (puzzles.isEmpty()) {
            throw new GlobalException(ErrorCode.PUZZLE_NOT_FOUND);
        }

        Puzzle puzzle = puzzles.get(ThreadLocalRandom.current().nextInt(puzzles.size()));

        PuzzleAttempt attempt = puzzleAttemptRepository.save(new PuzzleAttempt(guest, puzzle));

        List<PuzzleCellResponse> cells = puzzleCellRepository.findAllByPuzzle(puzzle)
                .stream()
                .map(cell -> new PuzzleCellResponse(
                        cell.getRowIndex(),
                        cell.getColIndex(),
                        cell.isBlock()
                ))
                .toList();

        List<PuzzleClueResponse> clues = puzzleClueRepository.findAllByPuzzle(puzzle)
                .stream()
                .map(clue -> new PuzzleClueResponse(
                        clue.getClueNumber(),
                        clue.getDirection(),
                        clue.getContent(),
                        clue.getStartRowIndex(),
                        clue.getStartColIndex(),
                        clue.getLength()
                ))
                .toList();

        return new StartRandomPuzzleResponse(
                attempt.getId(),
                puzzle.getId(),
                puzzle.getTitle(),
                puzzle.getRowSize(),
                puzzle.getColSize(),
                attempt.getStartedAt(),
                cells,
                clues
        );
    }
}
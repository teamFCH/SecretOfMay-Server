package com.example.team.fch.secretofmay.domain.puzzle.repository;

import com.example.team.fch.secretofmay.domain.puzzle.entity.Puzzle;
import com.example.team.fch.secretofmay.domain.puzzle.entity.PuzzleCell;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuzzleCellRepository extends JpaRepository<PuzzleCell, Long> {

    List<PuzzleCell> findAllByPuzzle(Puzzle puzzle);
}
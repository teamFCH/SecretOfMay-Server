package com.example.team.fch.secretofmay.domain.puzzle.repository;

import com.example.team.fch.secretofmay.domain.puzzle.entity.Puzzle;
import com.example.team.fch.secretofmay.domain.puzzle.entity.PuzzleClue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuzzleClueRepository extends JpaRepository<PuzzleClue, Long> {

    List<PuzzleClue> findAllByPuzzle(Puzzle puzzle);
}
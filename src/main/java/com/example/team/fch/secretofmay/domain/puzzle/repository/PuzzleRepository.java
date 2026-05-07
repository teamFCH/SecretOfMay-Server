package com.example.team.fch.secretofmay.domain.puzzle.repository;

import com.example.team.fch.secretofmay.domain.puzzle.entity.Puzzle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuzzleRepository extends JpaRepository<Puzzle, Long> {

    List<Puzzle> findAllByActiveTrue();
}
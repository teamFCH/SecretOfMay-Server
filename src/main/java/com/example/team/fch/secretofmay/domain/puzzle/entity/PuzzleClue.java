package com.example.team.fch.secretofmay.domain.puzzle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "puzzle_clue")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PuzzleClue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puzzle_id", nullable = false)
    private Puzzle puzzle;

    @Column(name = "clue_number", nullable = false)
    private int clueNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Direction direction;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 50)
    private String answer;

    @Column(name = "start_row_index", nullable = false)
    private int startRowIndex;

    @Column(name = "start_col_index", nullable = false)
    private int startColIndex;

    @Column(nullable = false)
    private int length;

    public PuzzleClue(Puzzle puzzle, int clueNumber, Direction direction, String content, String answer, int startRowIndex, int startColIndex, int length) {
        this.puzzle = puzzle;
        this.clueNumber = clueNumber;
        this.direction = direction;
        this.content = content;
        this.answer = answer;
        this.startRowIndex = startRowIndex;
        this.startColIndex = startColIndex;
        this.length = length;
    }
}
package com.example.team.fch.secretofmay.domain.puzzle.entity;

import jakarta.persistence.*;
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

    @Column(name = "exam_number", nullable = false)
    private Integer examNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Direction direction;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 50)
    private String answer;

    @Column(name = "start_row_index", nullable = false)
    private Integer startRowIndex;

    @Column(name = "start_col_index", nullable = false)
    private Integer startColIndex;

    @Column(nullable = false)
    private Integer length;

    public PuzzleClue(Puzzle puzzle, Integer examNumber, Direction direction, String content, String answer, Integer startRowIndex, Integer startColIndex, Integer length) {
        this.puzzle = puzzle;
        this.examNumber = examNumber;
        this.direction = direction;
        this.content = content;
        this.answer = answer;
        this.startRowIndex = startRowIndex;
        this.startColIndex = startColIndex;
        this.length = length;
    }
}
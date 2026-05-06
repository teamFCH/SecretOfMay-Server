package com.example.team.fch.secretofmay.domain.puzzle.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "puzzle_cell")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PuzzleCell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puzzle_id", nullable = false)
    private Puzzle puzzle;

    @Column(name = "row_index", nullable = false)
    private Integer rowIndex;

    @Column(name = "col_index", nullable = false)
    private Integer colIndex;

    @Column(name = "answer_char", length = 1)
    private String answerChar;

    @Column(name = "is_block", nullable = false)
    private Boolean isBlock;

    public PuzzleCell(Puzzle puzzle, Integer rowIndex, Integer colIndex, String answerChar, Boolean isBlock) {
        this.puzzle = puzzle;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.answerChar = answerChar;
        this.isBlock = isBlock;
    }
}
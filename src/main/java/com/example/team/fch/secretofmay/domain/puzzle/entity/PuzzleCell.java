package com.example.team.fch.secretofmay.domain.puzzle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private int rowIndex;

    @Column(name = "col_index", nullable = false)
    private int colIndex;

    @Column(name = "answer_char", length = 1)
    private String answerChar;

    @Column(name = "is_block", nullable = false)
    private boolean isBlock;

    public PuzzleCell(Puzzle puzzle, int rowIndex, int colIndex, String answerChar, boolean isBlock) {
        this.puzzle = puzzle;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.answerChar = answerChar;
        this.isBlock = isBlock;
    }
}
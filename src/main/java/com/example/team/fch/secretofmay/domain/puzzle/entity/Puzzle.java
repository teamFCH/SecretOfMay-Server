package com.example.team.fch.secretofmay.domain.puzzle.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "puzzle")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Puzzle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(name = "row_size", nullable = false)
    private Integer rowSize;

    @Column(name = "col_size", nullable = false)
    private Integer colSize;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Puzzle(String title, Integer rowSize, Integer colSize, Boolean isActive) {
        this.title = title;
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.isActive = isActive;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
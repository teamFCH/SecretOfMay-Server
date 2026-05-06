package com.example.team.fch.secretofmay.domain.puzzle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private int rowSize;

    @Column(name = "col_size", nullable = false)
    private int colSize;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Puzzle(String title, int rowSize, int colSize, boolean isActive) {
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
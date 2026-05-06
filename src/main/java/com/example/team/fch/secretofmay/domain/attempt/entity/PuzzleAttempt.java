package com.example.team.fch.secretofmay.domain.attempt.entity;

import com.example.team.fch.secretofmay.domain.guest.entity.Guest;
import com.example.team.fch.secretofmay.domain.puzzle.entity.Puzzle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "puzzle_attempt")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PuzzleAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puzzle_id", nullable = false)
    private Puzzle puzzle;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(name = "duration_ms")
    private Long durationMs;

    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public PuzzleAttempt(Guest guest, Puzzle puzzle) {
        this.guest = guest;
        this.puzzle = puzzle;
        this.startedAt = LocalDateTime.now();
        this.isCompleted = false;
    }

    public void complete(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
        this.durationMs = Duration.between(this.startedAt, submittedAt).toMillis();
        this.isCompleted = true;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
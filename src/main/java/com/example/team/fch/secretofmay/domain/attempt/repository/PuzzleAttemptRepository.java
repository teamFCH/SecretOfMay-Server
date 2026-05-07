package com.example.team.fch.secretofmay.domain.attempt.repository;

import com.example.team.fch.secretofmay.domain.attempt.entity.PuzzleAttempt;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuzzleAttemptRepository extends JpaRepository<PuzzleAttempt, Long> {

    List<PuzzleAttempt> findAllByCompletedTrueOrderByDurationMsAsc();

    List<PuzzleAttempt> findAllByCompletedTrueAndGuest_NicknameContainingOrderByDurationMsAsc(String nickname);

    long countByCompletedTrueAndDurationMsLessThan(Long durationMs);
}
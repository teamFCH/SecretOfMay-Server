package com.example.team.fch.secretofmay.domain.ranking.service.impl;

import com.example.team.fch.secretofmay.domain.attempt.entity.PuzzleAttempt;
import com.example.team.fch.secretofmay.domain.attempt.repository.PuzzleAttemptRepository;
import com.example.team.fch.secretofmay.domain.ranking.dto.response.RankingListResponse;
import com.example.team.fch.secretofmay.domain.ranking.dto.response.RankingResponse;
import com.example.team.fch.secretofmay.domain.ranking.service.GetRankingService;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetRankingServiceImpl implements GetRankingService {

    private final PuzzleAttemptRepository puzzleAttemptRepository;

    @Override
    @Transactional(readOnly = true)
    public RankingListResponse execute(String nickname) {
        List<PuzzleAttempt> attempts = getAttempts(nickname);
        AtomicLong rank = new AtomicLong(1);

        List<RankingResponse> rankings = attempts.stream()
                .map(attempt -> new RankingResponse(
                        rank.getAndIncrement(),
                        attempt.getGuest().getNickname(),
                        attempt.getPuzzle().getTitle(),
                        attempt.getDurationMs(),
                        formatDuration(attempt.getDurationMs()),
                        attempt.getSubmittedAt()
                ))
                .toList();

        return new RankingListResponse(rankings);
    }

    private List<PuzzleAttempt> getAttempts(String nickname) {
        if (nickname == null || nickname.isBlank()) {
            return puzzleAttemptRepository.findAllByCompletedTrueOrderByDurationMsAsc();
        }

        return puzzleAttemptRepository.findAllByCompletedTrueAndGuest_NicknameContainingOrderByDurationMsAsc(nickname);
    }

    private String formatDuration(long durationMs) {
        long minutes = durationMs / 60000;
        long seconds = (durationMs % 60000) / 1000;
        long hundredths = (durationMs % 1000) / 10;

        return String.format("%02d:%02d.%02d", minutes, seconds, hundredths);
    }
}
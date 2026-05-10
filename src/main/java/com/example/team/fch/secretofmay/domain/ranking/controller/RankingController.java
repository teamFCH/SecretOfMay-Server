package com.example.team.fch.secretofmay.domain.ranking.controller;

import com.example.team.fch.secretofmay.domain.ranking.dto.response.RankingListResponse;
import com.example.team.fch.secretofmay.domain.ranking.service.GetRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RankingController {

    private final GetRankingService getRankingService;

    @GetMapping("/api/ranking")
    public RankingListResponse getRanking(
            @RequestParam(required = false) String nickname
    ) {
        return getRankingService.execute(nickname);
    }
}
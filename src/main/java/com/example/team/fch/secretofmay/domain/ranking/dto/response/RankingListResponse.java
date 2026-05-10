package com.example.team.fch.secretofmay.domain.ranking.dto.response;

import java.util.List;

public record RankingListResponse(
        List<RankingResponse> rankings
) {
}
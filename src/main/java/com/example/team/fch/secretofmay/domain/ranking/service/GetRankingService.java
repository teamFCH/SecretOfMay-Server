package com.example.team.fch.secretofmay.domain.ranking.service;

import com.example.team.fch.secretofmay.domain.ranking.dto.response.RankingListResponse;

public interface GetRankingService {

    RankingListResponse execute(String nickname);
}
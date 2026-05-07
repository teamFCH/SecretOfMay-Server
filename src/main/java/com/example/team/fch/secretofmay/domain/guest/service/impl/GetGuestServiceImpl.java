package com.example.team.fch.secretofmay.domain.guest.service.impl;

import com.example.team.fch.secretofmay.domain.guest.dto.response.GuestMeResponse;
import com.example.team.fch.secretofmay.domain.guest.entity.Guest;
import com.example.team.fch.secretofmay.domain.guest.repository.GuestRepository;
import com.example.team.fch.secretofmay.domain.guest.service.GetGuestService;
import com.example.team.fch.secretofmay.global.exception.ErrorCode;
import com.example.team.fch.secretofmay.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetGuestServiceImpl implements GetGuestService {

    private final GuestRepository guestRepository;

    @Override
    @Transactional(readOnly = true)
    public GuestMeResponse execute(String sessionId) {
        Guest guest = getBySessionId(sessionId);

        return new GuestMeResponse(
                guest.getId(),
                guest.getNickname()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Guest getBySessionId(String sessionId) {
        return guestRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new GlobalException(ErrorCode.SESSION_NOT_FOUND));
    }
}
package com.example.team.fch.secretofmay.domain.guest.service.impl;

import com.example.team.fch.secretofmay.domain.guest.dto.request.CreateGuestRequest;
import com.example.team.fch.secretofmay.domain.guest.dto.response.CreateGuestResponse;
import com.example.team.fch.secretofmay.domain.guest.entity.Guest;
import com.example.team.fch.secretofmay.domain.guest.repository.GuestRepository;
import com.example.team.fch.secretofmay.domain.guest.service.CreateGuestService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateGuestServiceImpl implements CreateGuestService {

    private final GuestRepository guestRepository;

    @Override
    @Transactional
    public CreateGuestResponse execute(CreateGuestRequest request) {
        String sessionId = UUID.randomUUID().toString();

        Guest guest = guestRepository.save(new Guest(sessionId, request.nickname()));

        return new CreateGuestResponse(
                guest.getId(),
                guest.getSessionId(),
                guest.getNickname()
        );
    }
}
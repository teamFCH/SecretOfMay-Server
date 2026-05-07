package com.example.team.fch.secretofmay.domain.guest.dto.response;

public record CreateGuestResponse(
        Long guestId,
        String sessionId,
        String nickname
) {
}
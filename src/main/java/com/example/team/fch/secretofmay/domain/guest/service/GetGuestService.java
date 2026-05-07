package com.example.team.fch.secretofmay.domain.guest.service;

import com.example.team.fch.secretofmay.domain.guest.dto.response.GuestMeResponse;
import com.example.team.fch.secretofmay.domain.guest.entity.Guest;

public interface GetGuestService {

    GuestMeResponse execute(String sessionId);

    Guest getBySessionId(String sessionId);
}
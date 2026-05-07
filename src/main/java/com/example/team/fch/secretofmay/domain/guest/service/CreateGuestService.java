package com.example.team.fch.secretofmay.domain.guest.service;

import com.example.team.fch.secretofmay.domain.guest.dto.request.CreateGuestRequest;
import com.example.team.fch.secretofmay.domain.guest.dto.response.CreateGuestResponse;

public interface CreateGuestService {

    CreateGuestResponse execute(CreateGuestRequest request);
}
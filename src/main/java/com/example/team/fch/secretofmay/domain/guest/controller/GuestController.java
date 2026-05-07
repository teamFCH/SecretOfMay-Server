package com.example.team.fch.secretofmay.domain.guest.controller;

import com.example.team.fch.secretofmay.domain.guest.dto.request.CreateGuestRequest;
import com.example.team.fch.secretofmay.domain.guest.dto.response.CreateGuestResponse;
import com.example.team.fch.secretofmay.domain.guest.dto.response.GuestMeResponse;
import com.example.team.fch.secretofmay.domain.guest.service.CreateGuestService;
import com.example.team.fch.secretofmay.domain.guest.service.GetGuestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GuestController {

    private final CreateGuestService createGuestService;
    private final GetGuestService getGuestService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/guest")
    public CreateGuestResponse createGuest(@Valid @RequestBody CreateGuestRequest request) {
        return createGuestService.execute(request);
    }

    @GetMapping("/api/guest/me")
    public GuestMeResponse getMe(@RequestHeader("X-Session-Id") String sessionId) {
        return getGuestService.execute(sessionId);
    }
}
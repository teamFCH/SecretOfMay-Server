package com.example.team.fch.secretofmay.domain.health.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping("/check")
    public Map<String, String> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("statues", "OK");

        return response;
    }
}

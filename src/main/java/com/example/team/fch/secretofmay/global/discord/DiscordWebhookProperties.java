package com.example.team.fch.secretofmay.global.discord;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "discord")
public record DiscordWebhookProperties(
        String webhookUrl,
        boolean enabled,
        String environment
) {
}
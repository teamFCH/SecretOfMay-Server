package com.example.team.fch.secretofmay.global.discord;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscordStartupNotifier implements ApplicationListener<ApplicationReadyEvent> {

    private final DiscordWebhookClient discordWebhookClient;

    @Value("${spring.application.name:SecretOfMay}")
    private String applicationName;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        discordWebhookClient.sendStartupMessage(applicationName);
    }
}
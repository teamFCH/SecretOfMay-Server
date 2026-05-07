package com.example.team.fch.secretofmay.global.discord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class DiscordWebhookClient {

    private final DiscordWebhookProperties properties;
    private final RestClient restClient = RestClient.create();

    public void sendStartupMessage(String applicationName) {
        if (!properties.enabled() || properties.webhookUrl() == null || properties.webhookUrl().isBlank()) {
            return;
        }

        String startedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Map<String, Object> body = Map.of(
                "embeds", List.of(
                        Map.of(
                                "title", "🚀 Secret Of May 서버 실행 완료",
                                "description", "애플리케이션이 정상적으로 실행되었습니다.",
                                "color", 5763719,
                                "fields", List.of(
                                        Map.of("name", "애플리케이션", "value", applicationName, "inline", true),
                                        Map.of("name", "환경", "value", properties.environment(), "inline", true),
                                        Map.of("name", "시작 일시", "value", startedAt, "inline", false)
                                )
                        )
                )
        );

        restClient.post()
                .uri(properties.webhookUrl())
                .body(body)
                .retrieve()
                .toBodilessEntity();
    }
}
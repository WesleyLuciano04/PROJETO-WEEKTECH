package com.faculdade.techweek.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value; 
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;              
import org.springframework.http.MediaType;                 
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BrevoEmailService {

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${app.mail.remetente}")
    private String remetente;

    private final RestTemplate restTemplate = new RestTemplate();

    public void enviarEmail(String destinatario, String assunto, String conteudoHtml) {
        String url = "https://api.brevo.com/v3/smtp/email";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("sender", Map.of("email", remetente));
        body.put("to", List.of(Map.of("email", destinatario)));
        body.put("subject", assunto);
        body.put("htmlContent", conteudoHtml);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            restTemplate.postForEntity(url, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao enviar e-mail via Brevo API: " + e.getMessage());
        }
    }
}
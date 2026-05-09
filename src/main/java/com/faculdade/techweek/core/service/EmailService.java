package com.faculdade.techweek.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${app.mail.remetente}")
    private String remetente;

    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public void enviarConfirmacaoProposta(String destinatario, String nome, String tema) {
        String assunto = "Proposta recebida — II Tech Week 2026";
        String corpo = "Olá, " + nome + "!\n\n"
                + "Recebemos sua proposta de palestra:\n"
                + "→ " + tema + "\n\n"
                + "Nossa comissão irá avaliá-la e retornaremos em até 7 dias úteis.\n\n"
                + "Atenciosamente,\n"
                + "Comissão II Tech Week 2026";

        enviar(destinatario, assunto, corpo);
    }

    @Async
    public void enviarAprovacaoProposta(String destinatario, String nome, String tema) {
        String assunto = "Proposta aprovada — II Tech Week 2026";
        String corpo = "Parabéns, " + nome + "!\n\n"
                + "Sua proposta foi APROVADA:\n"
                + "→ " + tema + "\n\n"
                + "Entraremos em contato para definir data, horário e logística.\n\n"
                + "Atenciosamente,\n"
                + "Comissão II Tech Week 2026";

        enviar(destinatario, assunto, corpo);
    }

    @Async
    public void enviarReprovacaoProposta(String destinatario, String nome, String tema) {
        String assunto = "Resultado da sua proposta — II Tech Week 2026";
        String corpo = "Olá, " + nome + "!\n\n"
                + "Agradecemos o interesse em participar da II Tech Week 2026.\n"
                + "Infelizmente sua proposta não foi selecionada nesta edição:\n"
                + "→ " + tema + "\n\n"
                + "Esperamos contar com você em próximas edições!\n\n"
                + "Atenciosamente,\n"
                + "Comissão II Tech Week 2026";

        enviar(destinatario, assunto, corpo);
    }

    private void enviar(String destinatario, String assunto, String corpo) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", apiKey);

            Map<String, Object> body = new HashMap<>();
            body.put("sender", Map.of("email", remetente, "name", "II Tech Week 2026"));
            body.put("to", List.of(Map.of("email", destinatario)));
            body.put("subject", assunto);
            body.put("textContent", corpo);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            restTemplate.postForEntity("https://api.brevo.com/v3/smtp/email", request, String.class);
            log.info("E-mail enviado para: {}", destinatario);

        } catch (Exception ex) {
            log.error("Falha ao enviar e-mail para {}: {}", destinatario, ex.getMessage());
        }
    }
}
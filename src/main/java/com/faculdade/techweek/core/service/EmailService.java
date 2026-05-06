package com.faculdade.techweek.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.remetente}")
    private String remetente;

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
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setFrom(remetente);
            mensagem.setTo(destinatario);
            mensagem.setSubject(assunto);
            mensagem.setText(corpo);

            mailSender.send(mensagem);
            log.info("E-mail enviado para: {}", destinatario);

        } catch (Exception ex) {
            log.error("Falha ao enviar e-mail para {}: {}", destinatario, ex.getMessage());
        }
    }
}
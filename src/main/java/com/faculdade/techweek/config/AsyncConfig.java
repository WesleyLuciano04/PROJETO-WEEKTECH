package com.faculdade.techweek.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

//Necessário para que o EmailService envie e-mails sem bloquear a requisição.
@Configuration
@EnableAsync
public class AsyncConfig {
}
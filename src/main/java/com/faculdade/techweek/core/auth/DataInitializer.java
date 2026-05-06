package com.faculdade.techweek.core.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.faculdade.techweek.core.model.Usuario;
import com.faculdade.techweek.core.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_SENHA = "techweek2026";

    @Override
    public void run(String... args) {
        if (!usuarioRepository.existsByUsername(ADMIN_USERNAME)) {
            usuarioRepository.save(Usuario.builder()
                    .username(ADMIN_USERNAME)
                    .senha(passwordEncoder.encode(ADMIN_SENHA))
                    .role("ROLE_ADMIN")
                    .ativo(true)
                    .build());
        }
    }
}
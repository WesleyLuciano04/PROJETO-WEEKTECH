package com.faculdade.techweek.core.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SupabaseStorageService {

    @Value("${supabase.url:}")
    private String supabaseUrl;

    @Value("${supabase.key:}")
    private String supabaseKey;

    @Value("${supabase.bucket:}")
    private String bucket;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String uploadCurriculo(MultipartFile arquivo) throws IOException {

        String nomeOriginal = arquivo.getOriginalFilename();
        String nomeSanitizado = nomeOriginal
                .replaceAll("[^a-zA-Z0-9._-]", "_")
                .replaceAll("\\s+", "_");

        String nomeUnico = UUID.randomUUID() + "_" + nomeSanitizado;
        String objectPath = nomeUnico;

        String uploadUrl = supabaseUrl + "/storage/v1/object/" + bucket + "/" + objectPath;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uploadUrl))
                .header("Authorization", "Bearer " + supabaseKey)
                .header("Content-Type", "application/pdf")
                .header("x-upsert", "true")
                .POST(HttpRequest.BodyPublishers.ofByteArray(arquivo.getBytes()))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                log.error("Supabase upload falhou. Status: {} | Body: {}",
                        response.statusCode(), response.body());
                throw new IOException("Falha no upload para Supabase: " + response.body());
            }

            String publicUrl = supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + objectPath;
            log.info("Currículo salvo no Supabase: {}", publicUrl);
            return publicUrl;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Upload interrompido", e);
        }
    }
}
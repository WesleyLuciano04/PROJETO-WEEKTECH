package com.faculdade.techweek.web.Admin.Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faculdade.techweek.core.service.ExcelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Endpoint exclusivo para o admin baixar a planilha de participantes.
 * Acessível apenas por ROLE_ADMIN (garantido pelo SecurityConfig).
 */
@Controller
@RequestMapping("/admin/exportar")
@RequiredArgsConstructor
@Slf4j
public class ExcelController {

    private final ExcelService excelService;

    /**
     * GET /admin/exportar/participantes
     *
     * Gera e retorna a planilha Excel como download direto no navegador.
     * O nome do arquivo inclui a data/hora da geração.
     */
    @GetMapping("/participantes")
    public ResponseEntity<byte[]> exportarParticipantes() {
        try {
            byte[] planilha = excelService.gerarPlanilhaParticipantes();

            // Nome do arquivo com timestamp — ex: participantes_2026-10-20_19h30.xlsx
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH'h'mm"));
            String nomeArquivo = "participantes_" + timestamp + ".xlsx";

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + nomeArquivo + "\"")
                    .contentType(MediaType.parseMediaType(
                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(planilha);

        } catch (IOException ex) {
            log.error("Erro ao gerar planilha de participantes: {}", ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
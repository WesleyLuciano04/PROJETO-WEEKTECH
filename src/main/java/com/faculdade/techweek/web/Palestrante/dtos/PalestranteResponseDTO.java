package com.faculdade.techweek.web.Palestrante.dtos;

import java.time.LocalDateTime;

import com.faculdade.techweek.core.enums.StatusInscricao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PalestranteResponseDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String temaPalestra;
    private String briefing;
    private Integer duracaoMinutos;
    private String curriculoResumo;
    private String curriculoArquivoPath;
    private boolean autorizaDivulgacao;
    private StatusInscricao status;
    private String fotoUrl;
    private LocalDateTime criadoEm;
}
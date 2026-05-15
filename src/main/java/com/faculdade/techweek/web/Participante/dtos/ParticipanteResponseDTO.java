package com.faculdade.techweek.web.Participante.dtos;

import java.time.LocalDateTime;

import com.faculdade.techweek.core.enums.Curso;
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
public class ParticipanteResponseDTO {

    private Long id;
    private String nome;
    private String ra;
    private Curso curso;
    private String cursoDescricao;
    private Integer serie;
    private boolean coffeeBreak;
    private StatusInscricao status;
    private LocalDateTime criadoEm;
    private boolean temProjeto;
    private String nomeProjeto;
    private String descricaoProjeto;
    private boolean presencia_1;
    private boolean presencia_2;
    private boolean presencia_3;
}
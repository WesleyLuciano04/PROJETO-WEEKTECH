package com.faculdade.techweek.web.Participante.dtos;

import com.faculdade.techweek.core.enums.Curso;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ParticipanteDTO {

    @NotNull
    @Size(min = 3, max = 120)
    private String nome;

    @NotNull
    @Size(min = 10, max = 10)
    private String ra;

    @NotNull
    private Curso curso;

    @NotNull
    private Integer serie;

    private boolean coffeeBreak;

    private boolean temProjeto;

    private String nomeProjeto;

    private String descricaoProjeto;
}

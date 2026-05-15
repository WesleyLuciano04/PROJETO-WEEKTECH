package com.faculdade.techweek.web.Admin.dto;

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
public class DashboardDTO {

    private long totalParticipantes;
    private long participantesPendentes;
    private long participantesAprovados;
    private long participantesComCoffeeBreak;
    private long participantesComProjeto;
    private Long dia_1;
    private Long dia_2;
    private Long dia_3;

    private long totalPalestrantes;
    private long palestrantesPendentes;
    private long palestrantesAprovados;
    private long palestrantesReprovados;
}
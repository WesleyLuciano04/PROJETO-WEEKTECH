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

    private long totalPalestrantes;
    private long palestrantesPendentes;
    private long palestrantesAprovados;
    private long palestrantesReprovados;
}
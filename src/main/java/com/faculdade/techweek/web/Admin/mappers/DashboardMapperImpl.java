package com.faculdade.techweek.web.Admin.mappers;

import org.springframework.stereotype.Component;

import com.faculdade.techweek.core.enums.StatusInscricao;
import com.faculdade.techweek.core.service.PalestranteService;
import com.faculdade.techweek.core.service.ParticipanteService;
import com.faculdade.techweek.web.Admin.dto.DashboardDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DashboardMapperImpl implements DashboardMapper{

    private final ParticipanteService participanteService;
    private final PalestranteService palestranteService;

    public DashboardDTO montarDashboard() {
        
        return DashboardDTO.builder()
                .totalParticipantes(participanteService.contarTotal())
                .participantesPendentes(participanteService.contarPorStatus(StatusInscricao.PENDENTE))
                .participantesAprovados(participanteService.contarPorStatus(StatusInscricao.APROVADO))
                .participantesComCoffeeBreak(participanteService.contarComCoffeeBreak())
                .participantesComProjeto(participanteService.contarComProjeto())
                .totalPalestrantes(palestranteService.contarTotal())
                .palestrantesPendentes(palestranteService.contarPorStatus(StatusInscricao.PENDENTE))
                .palestrantesAprovados(palestranteService.contarPorStatus(StatusInscricao.APROVADO))
                .palestrantesReprovados(palestranteService.contarPorStatus(StatusInscricao.REPROVADO))
                .build();
    }
}

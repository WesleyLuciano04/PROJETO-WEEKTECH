package com.faculdade.techweek.core.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faculdade.techweek.core.model.Participante;
import com.faculdade.techweek.core.repository.ParticipanteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PresencaService {

    private final ParticipanteRepository participanteRepository;

    private static final LocalDate DIA_1 = LocalDate.of(2026, 05, 15);
    private static final LocalDate DIA_2 = LocalDate.of(2026, 05, 16);
    private static final LocalDate DIA_3 = LocalDate.of(2026, 05, 17);

    //private static final LocalTime INICIO = LocalTime.of(19, 0);
    // private static final LocalTime FIM    = LocalTime.of(23, 59);

    @Transactional
    public String registrarPresenca(String ra) {
        String raNormalizado = ra.trim();

        Participante participante = participanteRepository.findByRa(raNormalizado)
                .orElseThrow(() -> new IllegalArgumentException(
                        "R.A. não encontrado: " + raNormalizado + ". Verifique e tente novamente."));

        LocalDate hoje = LocalDate.now();
        LocalTime agora = LocalTime.now();

        /*boolean dentroDoHorario = !agora.isBefore(INICIO) && !agora.isAfter(FIM);

        if (!dentroDoHorario) {
            return "Registro de presença disponível apenas das 19h00 às 23h59.";
        }
                    */

        if (hoje.equals(DIA_1)) {
            if (participante.isPresencaDia1()) {
                return "Presença do Dia 1 já registrada, " + participante.getNome() + "!";
            }
            participante.setPresencaDia1(true);
            participanteRepository.save(participante);
            return "Presença do Dia 1 confirmada! Bem-vindo(a), " + participante.getNome() + "!";
        }

   
        if (hoje.equals(DIA_2)) {
            if (participante.isPresencaDia2()) {
                return "Presença do Dia 2 já registrada, " + participante.getNome() + "!";
            }
            participante.setPresencaDia2(true);
            participanteRepository.save(participante);
            return "Presença do Dia 2 confirmada! Bem-vindo(a), " + participante.getNome() + "!";
        }


        if (hoje.equals(DIA_3)) {
            if (participante.isPresencaDia3()) {
                return "Presença do Dia 3 já registrada, " + participante.getNome() + "!";
            }
            participante.setPresencaDia3(true);
            participanteRepository.save(participante);
            return "Presença do Dia 3 confirmada! Bem-vindo(a), " + participante.getNome() + "!";
        }


        return "Hoje não há registro de presença disponível.";
    }
}

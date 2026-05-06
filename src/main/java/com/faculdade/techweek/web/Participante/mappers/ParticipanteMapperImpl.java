package com.faculdade.techweek.web.Participante.mappers;

import org.springframework.stereotype.Component;

import com.faculdade.techweek.core.model.Participante;
import com.faculdade.techweek.core.model.ProjetoParticipante;
import com.faculdade.techweek.web.Participante.dtos.ParticipanteDTO;
import com.faculdade.techweek.web.Participante.dtos.ParticipanteResponseDTO;

@Component
public class ParticipanteMapperImpl implements ParticipanteMapper{

    public Participante toEntity(ParticipanteDTO dto) {
        Participante participante = Participante.builder()
                .nome(dto.getNome().trim())
                .ra(dto.getRa().trim())
                .curso(dto.getCurso())
                .serie(dto.getSerie())
                .coffeeBreak(dto.isCoffeeBreak())
                .build();

        if (dto.isTemProjeto()
                && dto.getNomeProjeto() != null
                && !dto.getNomeProjeto().isBlank()) {

            ProjetoParticipante projeto = ProjetoParticipante.builder()
                    .nomeProjeto(dto.getNomeProjeto().trim())
                    .descricao(dto.getDescricaoProjeto() != null
                            ? dto.getDescricaoProjeto().trim() : "")
                    .participante(participante)
                    .build();

            participante.setProjeto(projeto);
        }

        return participante;
    }

    public ParticipanteResponseDTO toResponseDTO(Participante p) {
        boolean temProjeto = p.getProjeto() != null;

        return ParticipanteResponseDTO.builder()
                .id(p.getId())
                .nome(p.getNome())
                .ra(p.getRa())
                .curso(p.getCurso())
                .cursoDescricao(p.getCurso().getDescricao())
                .serie(p.getSerie())
                .coffeeBreak(p.isCoffeeBreak())
                .status(p.getStatus())
                .criadoEm(p.getCriadoEm())
                .temProjeto(temProjeto)
                .nomeProjeto(temProjeto ? p.getProjeto().getNomeProjeto() : null)
                .descricaoProjeto(temProjeto ? p.getProjeto().getDescricao() : null)
                .build();
    }
}

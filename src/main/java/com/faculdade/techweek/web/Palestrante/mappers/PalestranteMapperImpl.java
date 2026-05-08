package com.faculdade.techweek.web.Palestrante.mappers;


import org.springframework.stereotype.Component;

import com.faculdade.techweek.core.model.Palestrante;
import com.faculdade.techweek.web.Palestrante.dtos.PalestranteDTO;
import com.faculdade.techweek.web.Palestrante.dtos.PalestranteResponseDTO;

@Component
public class PalestranteMapperImpl implements PalestranteMapper {

    public Palestrante toEntity(PalestranteDTO dto) {
        return Palestrante.builder()
                .nome(dto.getNome().trim())
                .telefone(dto.getTelefone().trim())
                .email(dto.getEmail().trim().toLowerCase())
                .temaPalestra(dto.getTemaPalestra().trim())
                .briefing(dto.getBriefing().trim())
                .curriculoResumo(dto.getCurriculoResumo().trim())
                .autorizaDivulgacao(dto.isAutorizaDivulgacao())
                .build();
    }

    public PalestranteResponseDTO toResponseDTO(Palestrante p) {
        return PalestranteResponseDTO.builder()
                .id(p.getId())
                .nome(p.getNome())
                .telefone(p.getTelefone())
                .email(p.getEmail())
                .temaPalestra(p.getTemaPalestra())
                .briefing(p.getBriefing())
                .curriculoResumo(p.getCurriculoResumo())
                .curriculoArquivoPath(p.getCurriculoArquivoPath())
                .autorizaDivulgacao(p.isAutorizaDivulgacao())
                .status(p.getStatus())
                .criadoEm(p.getCriadoEm())
                .build();
    }
}
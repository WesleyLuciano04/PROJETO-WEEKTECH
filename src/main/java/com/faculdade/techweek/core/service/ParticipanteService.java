package com.faculdade.techweek.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faculdade.techweek.core.enums.StatusInscricao;
import com.faculdade.techweek.core.model.Participante;
import com.faculdade.techweek.core.repository.ParticipanteRepository;
import com.faculdade.techweek.web.Admin.dto.AtualizarStatusDTO;
import com.faculdade.techweek.web.Participante.dtos.ParticipanteDTO;
import com.faculdade.techweek.web.Participante.dtos.ParticipanteResponseDTO;
import com.faculdade.techweek.web.Participante.mappers.ParticipanteMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;
    private final ParticipanteMapper participanteMapper;

    @Transactional
    public void inscrever(ParticipanteDTO dto) {
        if (participanteRepository.existsByRa(dto.getRa())) {
            throw new IllegalArgumentException(
                    "O RA " + dto.getRa() + " já possui uma inscrição cadastrada.");
        }

        if (dto.isTemProjeto()
                && (dto.getNomeProjeto() == null || dto.getNomeProjeto().isBlank())) {
            throw new IllegalStateException("Informe o nome do projeto para poder submetê-lo.");
        }

        Participante participante = participanteMapper.toEntity(dto);

        if (!dto.isTemProjeto()) {
            participante.setStatus(StatusInscricao.SEMPROJETO);
        }
        
        participanteRepository.save(participante);

    }

    @Transactional(readOnly = true)
    public List<ParticipanteResponseDTO> listarTodos() {
        return participanteRepository.findAll()
                .stream().map(participanteMapper::toResponseDTO).toList();
    }

    @Transactional(readOnly = true)
    public ParticipanteResponseDTO buscarPorId(Long id) {
        return participanteRepository.findById(id)
                .map(participanteMapper::toResponseDTO)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Participante não encontrado com id: " + id));
    }

    @Transactional
    public void atualizarStatus(Long id, AtualizarStatusDTO dto) {
        Participante participante = participanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Participante não encontrado com id: " + id));

        participante.setStatus(dto.getStatus());

        participanteRepository.save(participante);
    }

    public long contarTotal() {
        return participanteRepository.count();
    }

    public long contarPorStatus(StatusInscricao s) {
        return participanteRepository.countByStatus(s);
    }

    public long contarComCoffeeBreak() {
        return participanteRepository.countByCoffeeBreakTrue();
    }

    public long contarComProjeto() {
        return participanteRepository.findAllComProjeto().size();
    }

    public Long contarPresecaDia1() {
        return participanteRepository.countByPresencaDia1True();
    }

    public Long contarPresecaDia2() {
        return participanteRepository.countByPresencaDia2True();
    }

    public Long contarPresecaDia3() {
        return participanteRepository.countByPresencaDia3True();
    }
}
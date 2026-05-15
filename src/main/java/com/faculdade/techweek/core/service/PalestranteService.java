package com.faculdade.techweek.core.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faculdade.techweek.core.enums.StatusInscricao;
import com.faculdade.techweek.core.model.Palestrante;
import com.faculdade.techweek.core.repository.PalestranteRepository;
import com.faculdade.techweek.web.Admin.dto.AtualizarStatusDTO;
import com.faculdade.techweek.web.Palestrante.dtos.PalestranteDTO;
import com.faculdade.techweek.web.Palestrante.dtos.PalestranteResponseDTO;
import com.faculdade.techweek.web.Palestrante.mappers.PalestranteMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PalestranteService {

    private final PalestranteRepository palestranteRepository;
    private final PalestranteMapper palestranteMapper;
    private final EmailService emailService;
    private final SupabaseStorageService supabaseStorageService;

    @Transactional
    public void submeterProposta(PalestranteDTO dto) throws IOException {

        if (palestranteRepository.existsByEmail(dto.getEmail().trim().toLowerCase())) {
            throw new IllegalArgumentException(
                    "Já existe uma proposta com o e-mail: " + dto.getEmail());
        }

        Palestrante palestrante = palestranteMapper.toEntity(dto);

        if (dto.getCurriculoArquivo() != null && !dto.getCurriculoArquivo().isEmpty()) {
            palestrante.setCurriculoArquivoPath(supabaseStorageService.uploadCurriculo(dto.getCurriculoArquivo()));
        }

        Palestrante salvo = palestranteRepository.save(palestrante);
        log.info("Proposta salva: {} | {}", salvo.getNome(), salvo.getTemaPalestra());

        emailService.enviarConfirmacaoProposta(salvo.getEmail(), salvo.getNome(), salvo.getTemaPalestra());

    }

    @Transactional
    public void atualizarStatus(Long id, AtualizarStatusDTO dto) {
        Palestrante palestrante = palestranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Palestrante não encontrado: " + id));

        StatusInscricao anterior = palestrante.getStatus();
        palestrante.setStatus(dto.getStatus());
        palestranteRepository.save(palestrante);

        if (dto.getStatus() == StatusInscricao.APROVADO && anterior != StatusInscricao.APROVADO) {
            emailService.enviarAprovacaoProposta(palestrante.getEmail(), palestrante.getNome(),
                    palestrante.getTemaPalestra());

        } else if (dto.getStatus() == StatusInscricao.REPROVADO && anterior != StatusInscricao.REPROVADO) {
            emailService.enviarReprovacaoProposta(palestrante.getEmail(), palestrante.getNome(),
                    palestrante.getTemaPalestra());
        }
    }

    @Transactional(readOnly = true)
    public List<PalestranteResponseDTO> listarTodos() {
        return palestranteRepository.findAll()
                .stream().map(palestranteMapper::toResponseDTO).toList();
    }

    @Transactional(readOnly = true)
    public PalestranteResponseDTO buscarPorId(Long id) {
        return palestranteRepository.findById(id)
                .map(palestranteMapper::toResponseDTO)
                .orElseThrow(() -> new IllegalArgumentException("Palestrante não encontrado: " + id));
    }

    public long contarTotal() {
        return palestranteRepository.count();
    }

    public long contarPorStatus(StatusInscricao s) {
        return palestranteRepository.countByStatus(s);
    }
}
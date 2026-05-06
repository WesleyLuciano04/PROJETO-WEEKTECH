package com.faculdade.techweek.core.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.faculdade.techweek.core.enums.StatusInscricao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "palestrantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Palestrante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "tema_palestra", nullable = false, length = 200)
    private String temaPalestra;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String briefing;

    @Column(name = "duracao_minutos", nullable = false)
    private Integer duracaoMinutos;

    @Column(name = "curriculo_resumo", nullable = false, columnDefinition = "TEXT")
    private String curriculoResumo;

    @Column(name = "curriculo_arquivo_path", length = 300)
    private String curriculoArquivoPath;

    @Column(name = "autoriza_divulgacao", nullable = false)
    private boolean autorizaDivulgacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private StatusInscricao status = StatusInscricao.PENDENTE;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;
}
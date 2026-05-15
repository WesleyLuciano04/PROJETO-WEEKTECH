package com.faculdade.techweek.core.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.faculdade.techweek.core.enums.Curso;
import com.faculdade.techweek.core.enums.StatusInscricao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "participantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, unique = true, length = 10)
    private String ra;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Curso curso;

    @Column(nullable = false)
    private Integer serie;

    @Column(name = "coffee_break", nullable = false)
    @Builder.Default
    private boolean coffeeBreak = false;

    @OneToOne(mappedBy = "participante", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private ProjetoParticipante projeto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private StatusInscricao status = StatusInscricao.PENDENTE;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "presenca_dia_1", nullable = false)
    private boolean presencaDia1;

    @Column(name = "presenca_dia_2", nullable = false)
    private boolean presencaDia2;

    @Column(name = "presenca_dia_3", nullable = false)
    private boolean presencaDia3;

    public boolean getPreseca_dia_1() {
        return presencaDia1;
    }

    public boolean getPreseca_dia_2() {
        return presencaDia2;
    }

    public boolean getPreseca_dia_3() {
        return presencaDia3;
    }
}
package com.faculdade.techweek.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faculdade.techweek.core.enums.StatusInscricao;
import com.faculdade.techweek.core.model.Palestrante;

/**
 * Repositório JPA para Palestrante (RF03, RF07).
 */
@Repository
public interface PalestranteRepository extends JpaRepository<Palestrante, Long> {

    Optional<Palestrante> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Palestrante> findByStatus(StatusInscricao status);

    List<Palestrante> findByStatusOrderByNomeAsc(StatusInscricao status);

    long countByStatus(StatusInscricao status);
}
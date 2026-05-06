package com.faculdade.techweek.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.faculdade.techweek.core.enums.Curso;
import com.faculdade.techweek.core.enums.StatusInscricao;
import com.faculdade.techweek.core.model.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

    Optional<Participante> findByRa(String ra);

    boolean existsByRa(String ra);

    List<Participante> findByCurso(Curso curso);

    long countByStatus(StatusInscricao status);

    long countByCoffeeBreakTrue();
    
    @Query("SELECT p FROM Participante p WHERE p.projeto IS NOT NULL ORDER BY p.nome")
    List<Participante> findAllComProjeto();
}
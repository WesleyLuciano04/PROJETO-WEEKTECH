package com.faculdade.techweek.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faculdade.techweek.core.model.ProjetoParticipante;

@Repository
public interface ProjetoParticipanteRepository extends JpaRepository<ProjetoParticipante, Long> {

}
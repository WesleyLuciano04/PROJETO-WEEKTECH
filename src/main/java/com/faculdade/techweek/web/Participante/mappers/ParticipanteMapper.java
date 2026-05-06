package com.faculdade.techweek.web.Participante.mappers;

import com.faculdade.techweek.core.model.Participante;
import com.faculdade.techweek.web.Participante.dtos.ParticipanteDTO;
import com.faculdade.techweek.web.Participante.dtos.ParticipanteResponseDTO;

public interface ParticipanteMapper {

        Participante toEntity(ParticipanteDTO dto);
        
        ParticipanteResponseDTO toResponseDTO(Participante p);

}

package com.faculdade.techweek.web.Palestrante.mappers;

import com.faculdade.techweek.core.model.Palestrante;
import com.faculdade.techweek.web.Palestrante.dtos.PalestranteDTO;
import com.faculdade.techweek.web.Palestrante.dtos.PalestranteResponseDTO;

public interface PalestranteMapper {

    Palestrante toEntity(PalestranteDTO dto);

    PalestranteResponseDTO toResponseDTO(Palestrante p);
}

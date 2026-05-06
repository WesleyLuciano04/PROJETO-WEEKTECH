package com.faculdade.techweek.web.Admin.dto;

import com.faculdade.techweek.core.enums.StatusInscricao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarStatusDTO {

    private StatusInscricao status;
}
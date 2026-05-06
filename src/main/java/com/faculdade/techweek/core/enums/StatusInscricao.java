package com.faculdade.techweek.core.enums;

public enum StatusInscricao {

    PENDENTE("Pendente"),
    APROVADO("Aprovado"),
    REPROVADO("Reprovado"),
    SEMPROJETO("Sem projeto");

    private final String descricao;

    StatusInscricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
package com.faculdade.techweek.core.enums;

public enum Curso {

    ADS("Análise e Desenvolvimento de Sistemas"),
    ES("Engenharia de Software");

    private final String descricao;

    Curso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
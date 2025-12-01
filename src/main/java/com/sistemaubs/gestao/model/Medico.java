package com.sistemaubs.gestao.model;

public class Medico extends Profissional {

    private String crm;

    public Medico(Long id, String nome, String especialidade, String crm) {
        super(id,nome,especialidade);
        this.crm = crm;
    }
}


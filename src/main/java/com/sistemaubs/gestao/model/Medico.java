package com.sistemaubs.gestao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Medico extends Profissional {

    private String crm;

    public Medico(Long id, String nome, String especialidade, String crm) {
        super(id, nome, especialidade);
        this.crm = crm;
    }
}

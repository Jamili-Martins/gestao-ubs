package com.sistemaubs.gestao.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class Consulta {
    private Long id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime dataHora;
    private String observacoes;
}

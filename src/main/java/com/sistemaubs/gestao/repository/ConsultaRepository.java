package com.sistemaubs.gestao.repository;

import com.sistemaubs.gestao.model.Consulta;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ConsultaRepository {

    private List<Consulta> Consultas = new ArrayList<>();

    public List<Consulta> listarConsultas() {
        return Consultas;
    }

    public Consulta adicionarConsulta(Consulta Consulta) {
        Consultas.add(Consulta);
        return Consulta;
    }

    public Consulta pegarConsultaId(Long id) {
        for (Consulta Consulta : Consultas) {
            if (Objects.equals(Consulta.getId(), id)) {
                return Consulta;
            }
        }
        return null;
    }

    public void removerConsulta(Long id) {
        Consultas.removeIf(p -> p.getId().equals(id));
    }

    public Consulta editarConsulta(Long id, Consulta ConsultaEditada) {
        for (Consulta Consulta : Consultas) {
            if (Objects.equals(Consulta.getId(), id)) {
                Consulta.setPaciente(ConsultaEditada.getPaciente());
                Consulta.setMedico(ConsultaEditada.getMedico());
                Consulta.setDataHora(ConsultaEditada.getDataHora());
                Consulta.setObservacoes(ConsultaEditada.getObservacoes());
                return Consulta;
            }
        }
        return null;
    }
}
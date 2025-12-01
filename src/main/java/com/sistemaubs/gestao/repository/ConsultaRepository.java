package com.sistemaubs.gestao.repository;

import com.sistemaubs.gestao.model.Consulta;
import com.sistemaubs.gestao.persistence.JsonFileManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ConsultaRepository {

    private static final String CAMINHO_ARQUIVO = "src/main/resources/consultas.json";

    private List<Consulta> consultas;

    public ConsultaRepository() {
        this.consultas = JsonFileManager.carregarLista(CAMINHO_ARQUIVO, Consulta.class);
        if (consultas == null){
            this.consultas = new ArrayList<>();
        }
    }

    public List<Consulta> listarConsultas() {
        return consultas;
    }

    public Consulta adicionarConsulta(Consulta consulta) {
        consultas.add(consulta);
        JsonFileManager.salvarLista(CAMINHO_ARQUIVO, consultas);
        return consulta;
    }

    public Consulta pegarConsultaId(Long id) {
        for (Consulta consulta : consultas) {
            if (Objects.equals(consulta.getId(), id)) {
                return consulta;
            }
        }
        return null;
    }

    public void removerConsulta(Long id) {
        consultas.removeIf(p -> p.getId().equals(id));
        JsonFileManager.salvarLista(CAMINHO_ARQUIVO, consultas);
    }

    public Consulta editarConsulta(Long id, Consulta consultaEditada) {
        for (Consulta consulta : consultas) {
            if (Objects.equals(consulta.getId(), id)) {
                consulta.setPaciente(consultaEditada.getPaciente());
                consulta.setMedico(consultaEditada.getMedico());
                consulta.setDataHora(consultaEditada.getDataHora());
                consulta.setObservacoes(consultaEditada.getObservacoes());
                JsonFileManager.salvarLista(CAMINHO_ARQUIVO, consultas);
                return consulta;
            }
        }
        return null;
    }
}
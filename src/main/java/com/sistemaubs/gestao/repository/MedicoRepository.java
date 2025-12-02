package com.sistemaubs.gestao.repository;

import com.sistemaubs.gestao.model.Medico;
import com.sistemaubs.gestao.persistence.JsonFileManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class MedicoRepository {

    private static final String CAMINHO_ARQUIVO = "src/main/resources/data/medicos.json";

    private List<Medico> medicos;

    public MedicoRepository() {
        this.medicos = JsonFileManager.carregarLista(CAMINHO_ARQUIVO, Medico.class);
        if (medicos == null){
            this.medicos = new ArrayList<>();
        }
    }

    public List<Medico> listarMedicos() {
        return medicos;
    }

    public Medico adicionarMedico(Medico medico) {
        medicos.add(medico);
        JsonFileManager.salvarLista(CAMINHO_ARQUIVO, medicos);
        return medico;
    }

    public Medico pegarMedicoId(Long id) {
        for (Medico medico : medicos) {
            if (Objects.equals(medico.getId(), id)) {
                return medico;
            }
        }
        return null;
    }

    public void removerMedico(Long id) {
        medicos.removeIf(p -> p.getId().equals(id));
        JsonFileManager.salvarLista(CAMINHO_ARQUIVO, medicos);
    }

    public Medico editarMedico(Long id, Medico medicoEditado) {
        for (Medico medico : medicos) {
            if (Objects.equals(medico.getId(), id)) {
                medico.setNome(medicoEditado.getNome());
                medico.setEspecialidade(medicoEditado.getEspecialidade());
                medico.setCrm(medicoEditado.getCrm());
                JsonFileManager.salvarLista(CAMINHO_ARQUIVO, medicos);
                return medico;
            }
        }
        return null;
        }
    }
package com.sistemaubs.gestao.repository;

import com.sistemaubs.gestao.model.Paciente;
import com.sistemaubs.gestao.persistence.JsonFileManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class PacienteRepository {

    private final String CAMINHO_ARQUIVO = "data/pacientes.json";

    private List<Paciente> pacientes;

    public PacienteRepository() {
        this.pacientes = JsonFileManager.carregarLista(CAMINHO_ARQUIVO, Paciente.class);
    }

    public List<Paciente> listarPacientes() {
        return pacientes;
    }

    public Paciente adicionarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        JsonFileManager.salvarLista(CAMINHO_ARQUIVO, pacientes);
        return paciente;
    }

    public Paciente pegarPacienteId(Long id) {
        for (Paciente paciente : pacientes) {
            if (Objects.equals(paciente.getId(), id)) {
                return paciente;
            }
        }
        return null;
    }

    public void removerPaciente(Long id) {
        pacientes.removeIf(p -> p.getId().equals(id));
        JsonFileManager.salvarLista(CAMINHO_ARQUIVO, pacientes);
    }

    public Paciente editarPaciente(Long id, Paciente pacienteEditado) {
        for (Paciente paciente : pacientes) {
            if (Objects.equals(paciente.getId(), id)) {
                paciente.setCpf(pacienteEditado.getCpf());
                paciente.setNome(pacienteEditado.getNome());
                paciente.setTelefone(pacienteEditado.getTelefone());
                JsonFileManager.salvarLista(CAMINHO_ARQUIVO, pacientes);
                return paciente;
            }
        }
        return null;
    }
}

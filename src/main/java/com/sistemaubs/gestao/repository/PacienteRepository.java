package com.sistemaubs.gestao.repository;

import com.sistemaubs.gestao.model.Paciente;
import com.sistemaubs.gestao.persistence.JsonFileManager;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PacienteRepository {

    private static final String PASTA = "data";
    private static final String ARQUIVO = PASTA + "/pacientes.json";

    private List<Paciente> pacientes;

    public PacienteRepository() {
        inicializarEstrutura();
        carregarPacientes();
    }

    private void inicializarEstrutura() {
        File dir = new File(PASTA);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            JsonFileManager.salvarLista(ARQUIVO, new ArrayList<>());
        }
    }

    private void carregarPacientes() {
        List<Paciente> lista = JsonFileManager.carregarLista(ARQUIVO, Paciente.class);
        this.pacientes = (lista != null) ? lista : new ArrayList<>();
    }

    public List<Paciente> listarPacientes() {
        return pacientes;
    }

    public Paciente adicionarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        JsonFileManager.salvarLista(ARQUIVO, pacientes);
        return paciente;
    }

    public Paciente pegarPacienteId(Long id) {
        return pacientes.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public void removerPaciente(Long id) {
        pacientes.removeIf(p -> Objects.equals(p.getId(), id));
        JsonFileManager.salvarLista(ARQUIVO, pacientes);
    }

    public Paciente editarPaciente(Long id, Paciente pacienteEditado) {
        for (Paciente paciente : pacientes) {
            if (Objects.equals(paciente.getId(), id)) {

                paciente.setNome(pacienteEditado.getNome());
                paciente.setCpf(pacienteEditado.getCpf());
                paciente.setTelefone(pacienteEditado.getTelefone());

                JsonFileManager.salvarLista(ARQUIVO, pacientes);
                return paciente;
            }
        }
        return null;
    }
}

package com.sistemaubs.gestao.repository;

import com.sistemaubs.gestao.model.Paciente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PacienteRepository {

    private List<Paciente> pacientes = new ArrayList<>();

    public List<Paciente> listarPacientes() {
        return pacientes;
    }

    public Paciente adicionarPaciente(Paciente paciente) {
        pacientes.add(paciente);
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
    }

    public Paciente editarPaciente(Long id, Paciente pacienteEditado) {
        for (Paciente paciente : pacientes) {
            if (Objects.equals(paciente.getId(), id)) {
                paciente.setCpf(pacienteEditado.getCpf());
                paciente.setNome(pacienteEditado.getNome());
                paciente.setTelefone(pacienteEditado.getTelefone());
                return paciente;
            }
        }
        return null;
    }
}
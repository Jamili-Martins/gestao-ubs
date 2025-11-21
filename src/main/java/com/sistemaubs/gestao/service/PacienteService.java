package com.sistemaubs.gestao.service;


import com.sistemaubs.gestao.repository.PacienteRepository;
import com.sistemaubs.gestao.model.Paciente;
import com.sistemaubs.gestao.exception.PacienteNaoEncontradoException;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.listarPacientes();
    }

    public Paciente adicionarPaciente(Paciente paciente) {
        return pacienteRepository.adicionarPaciente(paciente);

    }

    public void removerPaciente(Long id) {
        Paciente paciente = pacienteRepository.pegarPacienteId(id);

        if(paciente == null){
            throw new PacienteNaoEncontradoException("Paciente com ID " + id + " não encontrado");
        }

        pacienteRepository.removerPaciente(id);
    }

    public Paciente pegarPacienteId(Long id){
        Paciente paciente = pacienteRepository.pegarPacienteId(id);

        if (paciente == null) {
            throw new PacienteNaoEncontradoException("Paciente com ID " + id + " não encontrado");
        }
        return pacienteRepository.pegarPacienteId(id);

    }

    public Paciente editarPaciente(Long id, Paciente pacienteEditado) {
        Paciente paciente = pacienteRepository.pegarPacienteId(id);
        if (paciente == null) {
            throw new PacienteNaoEncontradoException("Paciente com ID " + id + " não encontrado");
        }
        return pacienteRepository.editarPaciente(id, pacienteEditado);
    }
}


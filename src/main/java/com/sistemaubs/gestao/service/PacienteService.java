package com.sistemaubs.gestao.service;


import com.sistemaubs.gestao.repository.PacienteRepository;
import com.sistemaubs.gestao.model.Paciente;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente>   listarPacientes() {
        return pacienteRepository.listarPacientes();
    }

    public Paciente adicionarPaciente(Paciente paciente) {
        return pacienteRepository.adicionarPaciente(paciente);

    }

    public void removerPaciente(Long id) {

        pacienteRepository.removerPaciente(id);
    }

    public Paciente pegarPacienteId(Long id){
        return pacienteRepository.pegarPacienteId(id);

    }

    public Paciente editarPaciente(Long id, Paciente pacienteEditado) {
        return pacienteRepository.editarPaciente(id, pacienteEditado);
    }
}


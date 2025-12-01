package com.sistemaubs.gestao.service;


import com.sistemaubs.gestao.exception.ProfissionalNaoEncontradoException;
import com.sistemaubs.gestao.repository.MedicoRepository;
import com.sistemaubs.gestao.model.Medico;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository MedicoRepository;

    public MedicoService(MedicoRepository MedicoRepository) {
        this.MedicoRepository = MedicoRepository;
    }

    public List<Medico>   listarMedicos() {
        return MedicoRepository.listarMedicos();
    }

    public Medico adicionarMedico(Medico Medico) {
        return MedicoRepository.adicionarMedico(Medico);

    }

    public void removerMedico(Long id) {
        Medico medico = MedicoRepository.pegarMedicoId(id);
        if (medico == null) {
            throw new ProfissionalNaoEncontradoException(
                    "Médico com ID " + id + " não encontrado"
            );
        }
        MedicoRepository.removerMedico(id);
    }

    public Medico pegarMedicoId(Long id){
        Medico medico = MedicoRepository.pegarMedicoId(id);
        if (medico == null) {
            throw new ProfissionalNaoEncontradoException(
                    "Médico com ID " + id + " não encontrado");
        }
        return medico;

    }

    public Medico editarMedico(Long id, Medico medicoEditado) {
        Medico medico = MedicoRepository.pegarMedicoId(id);
        if (medico == null) {
            throw new ProfissionalNaoEncontradoException(
                    "Médico com ID " + id + " não encontrado");
        }
        return MedicoRepository.editarMedico(id, medicoEditado);
    }
}



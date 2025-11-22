package com.sistemaubs.gestao.service;


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

        MedicoRepository.removerMedico(id);
    }

    public Medico pegarMedicoId(Long id){
        return MedicoRepository.pegarMedicoId(id);

    }

    public Medico editarMedico(Long id, Medico medicoEditado) {
        return MedicoRepository.editarMedico(id, medicoEditado);
    }
}



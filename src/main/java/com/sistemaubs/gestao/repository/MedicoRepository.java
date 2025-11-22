package com.sistemaubs.gestao.repository;

import com.sistemaubs.gestao.model.Medico;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class MedicoRepository {

    private List<Medico> medicos = new ArrayList<>();

    public List<Medico> listarMedicos() {
        return medicos;
    }

    public Medico adicionarMedico(Medico medico) {
        medicos.add(medico);
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
    }

    public Medico editarMedico(Long id, Medico medicoEditado) {
        for (Medico medico : medicos) {
            if (Objects.equals(medico.getId(), id)) {
                medico.setNome(medicoEditado.getNome());
                medico.setEspecialidade(medicoEditado.getEspecialidade());
                return medico;
            }
        }
        return null;
        }
    }

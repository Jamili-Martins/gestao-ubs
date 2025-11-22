package com.sistemaubs.gestao.service;


import com.sistemaubs.gestao.repository.ConsultaRepository;
import com.sistemaubs.gestao.model.Consulta;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public List<Consulta>   listarConsultas() {
        return consultaRepository.listarConsultas();
    }

    public Consulta adicionarConsulta(Consulta consulta) {
        return consultaRepository.adicionarConsulta(consulta);

    }

    public void removerConsulta(Long id) {

        consultaRepository.removerConsulta(id);
    }

    public Consulta pegarConsultaId(Long id){
        return consultaRepository.pegarConsultaId(id);

    }

    public Consulta editarConsulta(Long id, Consulta consultaEditada) {
        return consultaRepository.editarConsulta(id, consultaEditada);
    }
}


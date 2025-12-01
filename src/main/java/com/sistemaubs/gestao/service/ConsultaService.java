package com.sistemaubs.gestao.service;

import com.sistemaubs.gestao.exception.ConsultaNaoEncontradaException;
import com.sistemaubs.gestao.exception.HorarioIndisponivelException;
import com.sistemaubs.gestao.exception.PacienteNaoEncontradoException;
import com.sistemaubs.gestao.exception.ProfissionalNaoEncontradoException;
import com.sistemaubs.gestao.model.Medico;
import com.sistemaubs.gestao.model.Paciente;
import com.sistemaubs.gestao.model.Consulta;
import com.sistemaubs.gestao.repository.ConsultaRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteService pacienteService, MedicoService medicoService) {
        this.consultaRepository = consultaRepository;
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
    }

    public List<Consulta>   listarConsultas() {
        return consultaRepository.listarConsultas();
    }

    public Consulta adicionarConsulta(Consulta consulta) {
        Paciente paciente = pacienteService.pegarPacienteId(consulta.getPaciente().getId());
        Medico medico = medicoService.pegarMedicoId(consulta.getMedico().getId());

        for (Consulta c : consultaRepository.listarConsultas()) {
            if (c.getMedico().getId().equals(medico.getId()) && c.getDataHora().equals(consulta.getDataHora())) {
                throw new HorarioIndisponivelException(
                        "O médico já possui consulta nesse horário");
            }
            if (c.getPaciente().getId().equals(paciente.getId()) &&  c.getDataHora().equals(consulta.getDataHora())) {
                throw new HorarioIndisponivelException("O médico já possui consulta nesse horário");
            }
        }
        consulta.setId(gerarNovoId());

        return consultaRepository.adicionarConsulta(consulta);

    }

    public void removerConsulta(Long id) {
        Consulta consulta = consultaRepository.pegarConsultaId(id);
        if (consulta == null) {
            throw new RuntimeException("Consulta com ID " + id + " não existe");
        }
        consultaRepository.removerConsulta(id);
    }

    public Consulta pegarConsultaId(Long id){
        Consulta consulta = consultaRepository.pegarConsultaId(id);
        if (consulta == null) {
            throw new RuntimeException("Consulta com ID " + id + " não existe");
        }
        return consulta;

    }

    public Consulta editarConsulta(Long id, Consulta consultaEditada) {
        Consulta consultaExistente = consultaRepository.pegarConsultaId(id);
        if (consultaExistente == null) {
            throw new ConsultaNaoEncontradaException("Consulta com ID " + id + " não existe");
        }
        pacienteService.pegarPacienteId(consultaEditada.getPaciente().getId());
        medicoService.pegarMedicoId(consultaEditada.getMedico().getId());

        for (Consulta c : consultaRepository.listarConsultas()) {
            if (!c.getId().equals(id) &&
                    c.getMedico().getId().equals(consultaEditada.getMedico().getId()) &&
                    c.getDataHora().equals(consultaEditada.getDataHora())) {

                throw new HorarioIndisponivelException(
                        "O médico já possui uma consulta agendada nesse horário.");
            }
            if (!c.getId().equals(id) && c.getPaciente().getId().equals(consultaEditada.getPaciente().getId()) && c.getDataHora().equals(consultaEditada.getDataHora())) {
                throw new HorarioIndisponivelException("O paciente já possui uma consulta agendada nesse horário.");
            };
        }        return consultaRepository.editarConsulta(id, consultaEditada);

    }
    private Long gerarNovoId(){
        List<Consulta> consultas = consultaRepository.listarConsultas();
        if (consultas.isEmpty()){
            return 1L;
        }
        return consultas.stream()
                .mapToLong(Consulta::getId)
                .max()
                .orElse(0L) + 1;
    }
}


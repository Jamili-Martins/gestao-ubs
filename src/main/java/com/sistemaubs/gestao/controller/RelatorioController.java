package com.sistemaubs.gestao.controller;

import com.sistemaubs.gestao.model.Consulta;
import com.sistemaubs.gestao.service.ConsultaService;
import com.sistemaubs.gestao.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sistemaubs.gestao.exception.RelatorioInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final ConsultaService consultaService;
    private final MedicoService medicoService;

    @Autowired
    public RelatorioController(ConsultaService consultaService, MedicoService medicoService) {
        this.consultaService = consultaService;
        this.medicoService = medicoService;
    }

    @GetMapping("/consultas/medico/{medicoId}")
    public List<Consulta> consultaMedico(@PathVariable Long medicoId){
        medicoService.pegarMedicoId(medicoId);
        return consultaService.listarConsultas().stream()
                .filter(c -> c.getMedico().getId().equals(medicoId))
                .toList();
    }

    @GetMapping("/consultas/data")
    public List<Consulta> consultaData(
            @RequestParam(required = false) String data,
            @RequestParam(required = false) String dataInicio,
            @RequestParam(required = false) String dataFim
    ){
        List<Consulta> consultas = consultaService.listarConsultas();

        boolean temDataUnica = data != null;
        boolean temIntervalo = dataInicio != null && dataFim != null;

        if (temDataUnica && temIntervalo) {
            throw new RelatorioInvalidoException(
                    "Use apenas ?data=YYYY-MM-DD ou ?dataInicio=YYYY-MM-DD&dataFim=YYYY-MM-DD, nunca ambos."
            );
        }

        if (!temDataUnica && !temIntervalo) {
            throw new RelatorioInvalidoException(
                    "Envie ?data=YYYY-MM-DD ou o intervalo ?dataInicio=YYYY-MM-DD&dataFim=YYYY-MM-DD."
            );
        }

        if (temDataUnica) {
            try {
                LocalDate target = LocalDate.parse(data);
                return consultas.stream()
                        .filter(c -> c.getDataHora().toLocalDate().equals(target))
                        .toList();
            } catch (Exception e) {
                throw new RelatorioInvalidoException("Formato inválido para 'data'. Use YYYY-MM-DD.");
            }
        }

        try {
            LocalDate inicio = LocalDate.parse(dataInicio);
            LocalDate fim = LocalDate.parse(dataFim);

            if (fim.isBefore(inicio)) {
                throw new RelatorioInvalidoException(
                        "dataFim não pode ser ANTERIOR a dataInicio."
                );
            }

            return consultas.stream()
                    .filter(c -> {
                        LocalDate d = c.getDataHora().toLocalDate();
                        return (d.isEqual(inicio) || d.isAfter(inicio))
                                && (d.isEqual(fim) || d.isBefore(fim));
                    })
                    .toList();

        } catch (Exception e) {
            throw new RelatorioInvalidoException("Formato inválido de intervalo. Use YYYY-MM-DD.");
        }
    }
}

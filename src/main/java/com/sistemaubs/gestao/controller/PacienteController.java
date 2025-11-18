package com.sistemaubs.gestao.controller;

import com.sistemaubs.gestao.model.Paciente;
import com.sistemaubs.gestao.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @PostMapping()
    public Paciente adicionarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.adicionarPaciente(paciente);
    }

    @DeleteMapping("/{id}")
    public void removerPaciente(@PathVariable Long id) {
        pacienteService.removerPaciente(id);
    }

    @GetMapping("/{id}")
    public Paciente pegarPacienteId(Long id){
        return pacienteService.pegarPacienteId(id);
    }
}


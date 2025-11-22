package com.sistemaubs.gestao.controller;

import com.sistemaubs.gestao.model.Medico;
import com.sistemaubs.gestao.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public List<Medico> listarMedicos() {
        return medicoService.listarMedicos();
    }

    @PostMapping()
    public Medico adicionarMedico(@RequestBody Medico medico) {
        return medicoService.adicionarMedico(medico);
    }

    @DeleteMapping("/{id}")
    public void removerMedico(@PathVariable Long id) {
        medicoService.removerMedico(id);
    }

    @GetMapping("/{id}")
    public Medico pegarMedicoId(@PathVariable Long id){
        return medicoService.pegarMedicoId(id);
    }
    @PutMapping("/{id}")
    public Medico editarMedico(@PathVariable Long id, @RequestBody Medico medicoEditado){
        return medicoService.editarMedico(id, medicoEditado);
    }

}


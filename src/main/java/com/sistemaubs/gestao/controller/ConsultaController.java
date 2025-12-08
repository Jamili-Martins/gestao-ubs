package com.sistemaubs.gestao.controller;

import com.sistemaubs.gestao.model.Consulta;
import com.sistemaubs.gestao.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService ConsultaService) {
        this.consultaService = ConsultaService;
    }

    @GetMapping
    public List<Consulta> listarConsultas() {
        return consultaService.listarConsultas();
    }

    @PostMapping()
    public Consulta adicionarConsulta(@RequestBody Consulta Consulta) {
        return consultaService.adicionarConsulta(Consulta);
    }

    @DeleteMapping("/{id}")
    public void removerConsulta(@PathVariable Long id) {
        consultaService.removerConsulta(id);
    }

    @GetMapping("/{id}")
    public Consulta pegarConsultaId(@PathVariable Long id){
        return consultaService.pegarConsultaId(id);
    }
    @PutMapping("/{id}")
    public Consulta editarConsulta(@PathVariable Long id, @RequestBody Consulta ConsultaEditado){
        return consultaService.editarConsulta(id, ConsultaEditado);

    }

}


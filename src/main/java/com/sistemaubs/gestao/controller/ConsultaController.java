package com.sistemaubs.gestao.controller;

import com.sistemaubs.gestao.model.Consulta;
import com.sistemaubs.gestao.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService ConsultaService;

    @Autowired
    public ConsultaController(ConsultaService ConsultaService) {
        this.ConsultaService = ConsultaService;
    }

    @GetMapping
    public List<Consulta> listarConsultas() {
        return ConsultaService.listarConsultas();
    }

    @PostMapping()
    public Consulta adicionarConsulta(@RequestBody Consulta Consulta) {
        return ConsultaService.adicionarConsulta(Consulta);
    }

    @DeleteMapping("/{id}")
    public void removerConsulta(@PathVariable Long id) {
        ConsultaService.removerConsulta(id);
    }

    @GetMapping("/{id}")
    public Consulta pegarConsultaId(@PathVariable Long id){
        return ConsultaService.pegarConsultaId(id);
    }
    @PutMapping("/{id}")
    public Consulta editarConsulta(@PathVariable Long id, @RequestBody Consulta ConsultaEditado){
        return ConsultaService.editarconsulta(id, ConsultaEditado);

    }

}


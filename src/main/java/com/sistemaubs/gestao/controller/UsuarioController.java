package com.sistemaubs.gestao.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3001") // libera o React na porta 3001
@RestController
public class UsuarioController {

    @GetMapping("/usuarios")
    public List<Map<String, Object>> listarUsuarios() {
        List<Map<String, Object>> usuarios = new ArrayList<>();
        usuarios.add(Map.of("id", 1, "nome", "João"));
        usuarios.add(Map.of("id", 2, "nome", "Maria"));
        usuarios.add(Map.of("id", 3, "nome", "Carlos"));
        return usuarios;
    }
}

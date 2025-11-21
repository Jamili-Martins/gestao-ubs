package com.sistemaubs.gestao.service;
import com.sistemaubs.gestao.model.Consulta;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ConsultaService {

    private List<Consulta> consultas = new ArrayList<>();

    public List<Consulta> listarConsultas() {
        return consultas;
    }

    public Consulta adicionarConsulta(Consulta consulta) {
        consultas.add(consulta);
        return consulta;
    }

    public void removerConsulta (Long id){
        consultas.removeIf(c -> c.getId().equals(id));
    }

    public Consulta pegarConsultaId(Long id) {
        for (Consulta consulta : consultas) {
            if (Objects.equals(consulta.getId(), id)) {
                return consulta;
            }
        }
        return null;
    }
}





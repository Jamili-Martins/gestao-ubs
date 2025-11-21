package com.sistemaubs.gestao.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonFileManager {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> void salvarLista(String caminho, List<T> lista) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(caminho), lista);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo JSON: " + caminho, e);
        }
    }

    public static <T> List<T> carregarLista(String caminho, Class<T> classe) {
        try {
            File arquivo = new File(caminho);

            if (!arquivo.exists()) {
                return new java.util.ArrayList<>();
            }

            CollectionType tipo = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, classe);

            return objectMapper.readValue(arquivo, tipo);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo JSON: " + caminho, e);
        }
    }
}

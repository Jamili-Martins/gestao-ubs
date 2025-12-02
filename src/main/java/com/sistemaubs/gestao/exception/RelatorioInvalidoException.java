package com.sistemaubs.gestao.exception;

public class RelatorioInvalidoException extends RuntimeException {
    public RelatorioInvalidoException(String mensagem){
        super(mensagem);
    }
}
package br.com.edson.validacao_senha.service;

import org.springframework.validation.BindingResult;

public interface SenhaService {
    boolean validarSenha(final BindingResult validacaoSenha);
}

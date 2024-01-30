package br.com.edson.validacao_senha.service;

import br.com.edson.validacao_senha.controller.domain.SenhaReponse;
import org.springframework.validation.BindingResult;

public interface SenhaService {
    SenhaReponse validarSenha(final BindingResult validacaoSenha);
}

package br.com.edson.validacao_senha.service;

import br.com.edson.validacao_senha.controller.domain.response.SenhaReponse;
import org.springframework.validation.BindingResult;

public interface SenhaService {
    SenhaReponse validarSenha(final BindingResult validacaoSenha);
}

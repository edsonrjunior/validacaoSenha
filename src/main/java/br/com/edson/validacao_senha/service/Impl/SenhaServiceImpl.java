package br.com.edson.validacao_senha.service.Impl;

import br.com.edson.validacao_senha.controller.domain.response.SenhaReponse;
import br.com.edson.validacao_senha.service.SenhaService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class SenhaServiceImpl implements SenhaService {
    @Override
    public SenhaReponse validarSenha(final BindingResult validacaoSenha) {
        return new SenhaReponse(!validacaoSenha.hasErrors());
    }
}

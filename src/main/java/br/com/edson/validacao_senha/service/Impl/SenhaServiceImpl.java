package br.com.edson.validacao_senha.service.Impl;

import br.com.edson.validacao_senha.service.SenhaService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class SenhaServiceImpl implements SenhaService {
    @Override
    public boolean validarSenha(final BindingResult validacaoSenha) {
        return !validacaoSenha.hasErrors();
    }
}

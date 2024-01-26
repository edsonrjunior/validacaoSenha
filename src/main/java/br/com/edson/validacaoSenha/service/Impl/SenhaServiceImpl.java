package br.com.edson.validacaoSenha.service.Impl;

import br.com.edson.validacaoSenha.domain.Senha;
import br.com.edson.validacaoSenha.service.SenhaService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class SenhaServiceImpl implements SenhaService {
    @Override
    public boolean validarSenha(Senha senha, BindingResult validacaoSenha) {
       //return !validacaoSenha.hasErrors(); //Clean Code
        return validacaoSenha.hasErrors() ? false : true;
    }
}

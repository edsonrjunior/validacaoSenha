package br.com.edson.validacaoSenha.service;

import br.com.edson.validacaoSenha.domain.Senha;
import org.springframework.validation.BindingResult;


public interface SenhaService {

   boolean validarSenha(Senha senha, BindingResult bindingResult);

}

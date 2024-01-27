package br.com.edson.validacao_senha.service;

import br.com.edson.validacao_senha.domain.Senha;
import org.springframework.validation.BindingResult;


public interface SenhaService {

   boolean validarSenha(Senha senha, BindingResult validacaoSenha);

}

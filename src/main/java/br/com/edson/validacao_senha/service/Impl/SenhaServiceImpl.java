package br.com.edson.validacao_senha.service.Impl;

import br.com.edson.validacao_senha.controller.domain.response.SenhaReponse;
import br.com.edson.validacao_senha.service.SenhaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Slf4j
@Service
public class SenhaServiceImpl implements SenhaService {

    @Override
    public SenhaReponse validarSenha(final BindingResult validacaoSenha, final String correlationId) {
        if (validacaoSenha.hasErrors()) { //TODO Utilizar ternário
            log.info("A senha do correlationId {} possui erros ", correlationId); //TODO Substituir por exception
            return new SenhaReponse(false);
        } else {
            return new SenhaReponse(true);
        }
    }
}

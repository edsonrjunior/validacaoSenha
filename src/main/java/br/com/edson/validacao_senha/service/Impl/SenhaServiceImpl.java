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
        if (validacaoSenha.hasErrors()) {
            log.info("A senha do correlationId " + correlationId + " possui erros");
            return new SenhaReponse(false);
        } else {
            log.info("A senha do correlationId " + correlationId + " não possui erros");
            return new SenhaReponse(true);
        }
    }
}

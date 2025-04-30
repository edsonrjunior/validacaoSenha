package br.com.edson.validacao_senha.facade;

import br.com.edson.validacao_senha.controller.domain.response.SenhaReponse;
import br.com.edson.validacao_senha.service.Impl.SenhaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SenhaFacade {
    private SenhaServiceImpl senhaService;

    public SenhaReponse validarSenha(final BindingResult validacaoSenha) {
        return senhaService.validarSenha(validacaoSenha, UUID.randomUUID().toString());
    }

}

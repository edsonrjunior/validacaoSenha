package br.com.edson.validacao_senha.facade;

import br.com.edson.validacao_senha.controller.domain.response.SenhaReponse;
import br.com.edson.validacao_senha.service.Impl.SenhaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@AllArgsConstructor
public class SenhaFacade {
    private SenhaServiceImpl senhaService;

    public SenhaReponse validarSenha(BindingResult validacaoSenha, String correlationId) {
        return senhaService.validarSenha(validacaoSenha, correlationId);
    }

}

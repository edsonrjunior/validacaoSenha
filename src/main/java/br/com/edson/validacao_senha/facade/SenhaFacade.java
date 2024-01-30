package br.com.edson.validacao_senha.facade;

import br.com.edson.validacao_senha.controller.domain.SenhaReponse;
import br.com.edson.validacao_senha.service.Impl.SenhaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class SenhaFacade {
    @Autowired
    private SenhaServiceImpl senhaService;

    public SenhaReponse validarSenha(BindingResult bindingResult) {
        return senhaService.validarSenha(bindingResult);
    }

}

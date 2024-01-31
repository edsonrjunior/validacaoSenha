package br.com.edson.validacao_senha;

import br.com.edson.validacao_senha.controller.SenhaController;
import br.com.edson.validacao_senha.facade.SenhaFacade;
import br.com.edson.validacao_senha.service.Impl.SenhaServiceImpl;
import br.com.edson.validacao_senha.service.SenhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidacaoSenhaApplicationTests {

    @Autowired
    private SenhaController senhaController;
    @Autowired
    private SenhaFacade senhaFacade;

    @Autowired
    SenhaServiceImpl senhaService;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(senhaController);
        Assertions.assertNotNull(senhaFacade);
        Assertions.assertNotNull(senhaService);
    }

}

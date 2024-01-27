package br.com.edson.validacao_senha.controller;


import br.com.edson.validacao_senha.domain.Senha;
import br.com.edson.validacao_senha.service.SenhaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SenhaControllerTest {


    @InjectMocks
    private SenhaController senhaController;
    @Mock
    private SenhaService senhaService;

    @Mock
    private BindingResult bindingResult;


    @Test
    void testValidacaoSenhaValida() {
        Senha senha = new Senha("SenhaValida123");
        when(senhaService.validarSenha(senha, bindingResult)).thenReturn(true);
        boolean result = senhaController.isValid(senha, bindingResult);
        assertTrue(result);
    }

    @Test
    void testValidacaoSenhaInvalida() {
        Senha senha = new Senha("SenhaInvalida");
        when(senhaService.validarSenha(senha, bindingResult)).thenReturn(false);
        boolean result = senhaController.isValid(senha, bindingResult);
        assertFalse(result);
    }


}
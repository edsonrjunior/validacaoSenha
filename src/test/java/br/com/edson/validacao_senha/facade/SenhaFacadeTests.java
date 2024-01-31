package br.com.edson.validacao_senha.facade;

import br.com.edson.validacao_senha.controller.domain.response.SenhaReponse;
import br.com.edson.validacao_senha.service.Impl.SenhaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SenhaFacadeTests {

    @InjectMocks
    private SenhaFacade senhaFacade;

    @Mock
    private SenhaServiceImpl senhaService;

    @Mock
    private BindingResult bindingResult;

    @Test
    @DisplayName("Quando a senhaResponse for true deve retornar true no endpoint")
    void deveRetornarTrueQuandoORetornoDaValidacaoDaSenhaForTrue() {
        when(senhaService.validarSenha(bindingResult)).thenReturn(new SenhaReponse(true));

        SenhaReponse validarSenha = senhaFacade.validarSenha(bindingResult);

        assertTrue(validarSenha.isValid());
        verify(senhaService, times(1)).validarSenha(bindingResult);
    }

    @Test
    @DisplayName("Quando a senhaResponse for false deve retornar false no endpoint")
    void deveRetornarFalsoQuandoORetornoDaValidacaoDaSenhaForFalso() {
        when(senhaService.validarSenha(bindingResult)).thenReturn(new SenhaReponse(false));

        SenhaReponse validarSenha = senhaFacade.validarSenha(bindingResult);

        assertFalse(validarSenha.isValid());
        verify(senhaService, times(1)).validarSenha(bindingResult);
    }
}
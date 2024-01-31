package br.com.edson.validacao_senha.service.Impl;

import br.com.edson.validacao_senha.controller.domain.response.SenhaReponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class SenhaServiceImplTest {

    @InjectMocks
    private SenhaServiceImpl senhaService;

    @Mock
    private BindingResult validacaoSenha;

    @Test
    @DisplayName("Quando o bindingResult for true, deve retornar false ")
    void deveRetornarTrueQuandoHasErrosForFalse() {

        when(validacaoSenha.hasErrors()).thenReturn(true);

        SenhaReponse senhaReponse = senhaService.validarSenha(validacaoSenha);

        assertFalse(senhaReponse.isValid());
        verify(validacaoSenha, times(1)).hasErrors();
    }

    @Test
    @DisplayName("Quando o bindingResult for false, deve retornar true ")
    void deveRetornarFalseQuandoHasErrosForTrue() {

        when(validacaoSenha.hasErrors()).thenReturn(false);

        SenhaReponse senhaReponse = senhaService.validarSenha(validacaoSenha);

        assertTrue(senhaReponse.isValid());
        verify(validacaoSenha, times(1)).hasErrors();
    }
}
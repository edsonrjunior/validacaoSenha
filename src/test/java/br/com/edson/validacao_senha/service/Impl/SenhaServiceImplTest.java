package br.com.edson.validacao_senha.service.Impl;

import br.com.edson.validacao_senha.domain.Senha;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        Senha senha = new Senha("");
        boolean isSenhaValida = senhaService.validarSenha(senha, validacaoSenha);

        assertFalse(isSenhaValida);
        verify(validacaoSenha, times(1)).hasErrors();
    }

    @Test
    @DisplayName("Quando o bindingResult for false, deve retornar true ")
    void deveRetornarFalseQuandoHasErrosForTrue() {

        when(validacaoSenha.hasErrors()).thenReturn(false);

        Senha senha = new Senha("");
        boolean isSenhaValida = senhaService.validarSenha(senha, validacaoSenha);

        assertTrue(isSenhaValida);
        verify(validacaoSenha, times(1)).hasErrors();
    }
}
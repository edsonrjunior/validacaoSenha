package br.com.edson.validacao_senha.service.Impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

@ExtendWith(MockitoExtension.class)
class SenhaServiceImplTest {

    @InjectMocks
    private SenhaServiceImpl senhaService;

    @Mock
    private BindingResult validacaoSenha;

//    @Test
//    @DisplayName("Quando o bindingResult for true, deve retornar false ")
//    void deveRetornarTrueQuandoHasErrosForFalse() {
//
//        when(validacaoSenha.hasErrors()).thenReturn(true);
//
//        boolean isSenhaValida = senhaService.validarSenha(validacaoSenha);
//
//        assertFalse(isSenhaValida);
//        verify(validacaoSenha, times(1)).hasErrors();
//    }

//    @Test
//    @DisplayName("Quando o bindingResult for false, deve retornar true ")
//    void deveRetornarFalseQuandoHasErrosForTrue() {
//
//        when(validacaoSenha.hasErrors()).thenReturn(false);
//
//        boolean isSenhaValida = senhaService.validarSenha(validacaoSenha);
//
//        assertTrue(isSenhaValida);
//        verify(validacaoSenha, times(1)).hasErrors();
//    }
}
package br.com.edson.validacao_senha.facade;

import br.com.edson.validacao_senha.service.Impl.SenhaServiceImpl;
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
class SenhaFacadeTest {

    @InjectMocks
    private SenhaFacade senhaFacade;

    @Mock
    private SenhaServiceImpl senhaService;

    @Mock
    private BindingResult validacaoSenha;

//    @Test
//    @DisplayName("Deve retornar true quando a validacao estiver correta")
//    void DeveRotornarTrueQuandoORetornoForTrue() {
//        when(senhaFacade.validarSenha(validacaoSenha)).thenReturn(true);
//
//        boolean resultado = senhaFacade.validarSenha(validacaoSenha);
//
//        assertTrue(resultado);
//        verify(senhaService, times(1)).validarSenha(validacaoSenha);
//    }

//   @Test
//    @DisplayName("Deve retornar false quando a validacao estiver incorreta")
//    void DeveRotornarFalseQuandoORetornoForFalse() {
//        when(senhaFacade.validarSenha(validacaoSenha)).thenReturn(false);
//
//        boolean resultado = senhaFacade.validarSenha(validacaoSenha);
//
//        assertFalse(resultado);
//        verify(senhaService, times(1)).validarSenha(validacaoSenha);
//    }
}
package br.com.edson.validacao_senha.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SenhaTest {

    @Test
    void deveRetornarFalseSenhaEmBranco() {
        String senha = "";
        var resultado = Senha.isRegexValido(senha);
        Assertions.assertFalse(resultado);
    }

    @Test
    void deveRetornarFalseDigitosMinusculosEDuplicados() {
        String senha = "aa";
        var resultado = Senha.isRegexValido(senha);
        Assertions.assertFalse(resultado);
    }
    @Test
    void deveRetornarFalseDigitosMinusculos() {
        String senha = "ab";
        var resultado = Senha.isRegexValido(senha);
        Assertions.assertFalse(resultado);
    }

    @Test
    void deveRetornarFalseSemCaracteresEspeciais() {
        String senha = "AAAbbbCc";
        var resultado = Senha.isRegexValido(senha);
        Assertions.assertFalse(resultado);
    }
    @Test
    void deveRetornarFalseQuandoDigitoDuplicado() {
        String senha = "AbTp9!foo";
        var resultado = Senha.isRegexValido(senha);
        Assertions.assertFalse(resultado);
    }

    @Test
    void deveRetornarFalseQuandoDigitoDuplicado2() {
        String senha = "AbTp9!foA";
        var resultado = Senha.isRegexValido(senha);
        Assertions.assertFalse(resultado);
    }

    @Test
    void deveRetornarFalseQuandoHaEspacos() {
        String senha = "AbTp9 fok";
        var resultado = Senha.isRegexValido(senha);
        Assertions.assertFalse(resultado);
    }

    @Test
    @DisplayName("Deve retornar true quando atender todos requisitos")
    void deveRetornarTrue() {
        String senha = "AbTp9!fok";
        var resultado = Senha.isRegexValido(senha);
        Assertions.assertTrue(resultado);
    }

}
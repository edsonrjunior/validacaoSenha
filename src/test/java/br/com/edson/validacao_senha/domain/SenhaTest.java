package br.com.edson.validacao_senha.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SenhaTest {

    private static final String senhaEmbranco = "";
    private static final String senhaApenasLetrasMinusculasEDuplicados = "aa";
    private static final String senhaLetrasMinusculas = "ab";
    private static final String senhaSemCaracteresEspeciais = "AAAbbbCc";
    private static final String senhaLetraODuplicada = "AbTp9!foo";
    private static final String senhaLetraADuplicada = "AbTp9!foA";
    private static final String senhaComEspaco = "AbTp9 fok";
    private static final String senhaValida = "AbTp9!fok";

    @ParameterizedTest
    @ValueSource(strings = {
            senhaEmbranco,
            senhaApenasLetrasMinusculasEDuplicados,
            senhaLetrasMinusculas,
            senhaSemCaracteresEspeciais,
            senhaLetraODuplicada,
            senhaLetraADuplicada,
            senhaComEspaco})
    @DisplayName("Deve retornar false para senhas invalidas")
    void deveValidarSenhasInvalidas(String senhaValida) {
        var resultado = Senha.isRegexValido(senhaValida);
        Assertions.assertFalse(resultado);
    }


    @Test
    @DisplayName("Deve retornar true quando atender todos requisitos")
    void deveRetornarTrue() {
        var resultado = Senha.isRegexValido(senhaValida);
        Assertions.assertTrue(resultado);
    }

}
package br.com.edson.validacao_senha.controller.domain.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SenhaTest {

    private static final String SENHA_EM_BRANCO = "";
    private static final String SENHA_APENAS_LETRAS_MINUSCULAS_E_DUPLICADOS = "aa";
    private static final String SENHA_LETRAS_MINUSCULAS = "ab";
    private static final String SENHA_SEM_CARACTERES_ESPECIAIS = "AAAbbbCc";
    private static final String SENHA_LETRA_O_DUPLICADA = "AbTp9!foo";
    private static final String SENHA_LETRA_A_DUPLICADA = "AbTp9!foA";
    private static final String SENHA_COM_ESPACO = "AbTp9 fok";
    private static final String SENHA_COM_CARACTERE_NAO_PERMITIDO = "AbCp9!fok/";
    private static final String SENHA_COM_CARACTERE_NAO_PERMITIDO_2 = "AbCp9!fok,";
    private static final String SENHA_COM_CARACTERE_NAO_PERMITIDO_3 = "AbCp9!fok>";
    private static final String SENHA_VALIDA = "AbTp9!fok";

    @ParameterizedTest
    @ValueSource(strings = {
            SENHA_EM_BRANCO,
            SENHA_APENAS_LETRAS_MINUSCULAS_E_DUPLICADOS,
            SENHA_LETRAS_MINUSCULAS,
            SENHA_SEM_CARACTERES_ESPECIAIS,
            SENHA_LETRA_O_DUPLICADA,
            SENHA_LETRA_A_DUPLICADA,
            SENHA_COM_ESPACO,
            SENHA_COM_CARACTERE_NAO_PERMITIDO,
            SENHA_COM_CARACTERE_NAO_PERMITIDO_2,
            SENHA_COM_CARACTERE_NAO_PERMITIDO_3})
    @DisplayName("Deve retornar false para senhas invalidas")
    void deveValidarSenhasInvalidas(String senhaValida) {
        boolean resultado = Senha.isRegexValido(senhaValida);
        Assertions.assertFalse(resultado);
    }


    @Test
    @DisplayName("Deve retornar true quando atender todos requisitos")
    void deveRetornarTrue() {
        boolean resultado = Senha.isRegexValido(SENHA_VALIDA);
        Assertions.assertTrue(resultado);
    }

}
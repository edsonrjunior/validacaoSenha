package br.com.edson.validacao_senha.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Senha {

    /**
     * (?!.*(.).*\1)         - Nao permitir caracteres duplicados
     * (?!.*\s)              - Nao permitir espacos em branco
     * (?=.*\d)              - Permitir digitos
     * (?=.*[a-z][A-Z])      - Permitir letras minusculas e maiusculas
     * (?=.*[!@#$%^&*()-+]+) - permitir as caracteres da lista
     * .{9,}                 - Permitir que a senha tenha ao menos 9 caracteres
     */

    private static final String REGEX_SENHA = "^(?!.*(.).*\\1)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+-]+).{9,}$";

    @NotNull
    @Pattern(regexp = REGEX_SENHA)
    String password;
    public static boolean isRegexValido(String senha){
        return senha.matches(REGEX_SENHA);
    }

}

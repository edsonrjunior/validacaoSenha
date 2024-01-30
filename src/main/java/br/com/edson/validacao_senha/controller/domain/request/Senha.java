package br.com.edson.validacao_senha.controller.domain.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Senha {

    /**
     * (?!.*(.).*\1)                    - Nao permitir caracteres duplicados
     * (?!.*\s)                         - Nao permitir espacos em branco
     * (?=.*\d)                         - Permitir digitos
     * (?=.*[a-z])                      - Permitir letras minusculas
     * (?=.*[A-Z])                      - Permitir letras maiusculas
     * (?=.*[!@#$%^&*()+-])             - permitir as caracteres da lista
     * (?!.*[^a-zA-Z0-9!@#$%^&*()+-]+)  - Negatiava para nao permitir nenhum caracter especial diferente da lista
     * .{9,}                            - Permitir que a senha tenha ao menos 9 caracteres
     */

    private static final String REGEX_SENHA =
            "^(?!.*(.).*\\1)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+-])(?!.*[^a-zA-Z0-9!@#$%^&*()+-]+).{9,}$";

    @NotNull
    @Pattern(regexp = REGEX_SENHA)
    String password;

    public static boolean isRegexValido(String senha) {
        return senha.matches(REGEX_SENHA);
    }

}

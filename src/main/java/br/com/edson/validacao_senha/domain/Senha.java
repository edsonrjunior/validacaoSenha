package br.com.edson.validacao_senha.domain;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

    @Pattern(regexp = "(?!.*(.).*\\1)(?!.*\\s)(?=.*\\d)(?=.*[a-z][A-Z])(?=.*[!@#$%^&*()-+]+).{9,}$")
    String password;
}

package br.com.edson.validacaoSenha.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Senha {

    /**
     * aa
     * ab
     * AAAbbbCc
     * AbTp9!foo
     * AbTp9!foA
     * AbTp9 fok
     * AbTp9!fok
     */

    @NotNull
    @NotBlank (message = "Senha inválida" )
    @Pattern(regexp = "^(?!.*(.).*\\1)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*()-+]).{9,}$")
    String senha;
}

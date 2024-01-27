package br.com.edson.validacao_senha.controller;

import br.com.edson.validacao_senha.domain.Senha;
import br.com.edson.validacao_senha.service.SenhaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"validar-senha"})
@AllArgsConstructor
public class SenhaController {

    private final SenhaService senhaService;

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public boolean isValid(@RequestBody @Valid Senha senha, BindingResult validacaoSenha) {
        return senhaService.validarSenha(senha, validacaoSenha);
    }

}

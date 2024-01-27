package br.com.edson.validacao_senha.controller;

import br.com.edson.validacao_senha.domain.Senha;
import br.com.edson.validacao_senha.service.SenhaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

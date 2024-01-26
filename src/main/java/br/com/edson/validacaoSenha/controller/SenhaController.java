package br.com.edson.validacaoSenha.controller;

import br.com.edson.validacaoSenha.domain.Senha;
import br.com.edson.validacaoSenha.service.SenhaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({"senha"})
public class SenhaController {

    private final SenhaService senhaService;

    public SenhaController(SenhaService senhaService) {
        this.senhaService = senhaService;
    }

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public boolean isValid(@RequestBody @Valid Senha senha, BindingResult validacaoSenha) {
        return senhaService.validarSenha(senha, validacaoSenha);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> exceptionsHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

}

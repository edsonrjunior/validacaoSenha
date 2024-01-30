package br.com.edson.validacao_senha.controller;

import br.com.edson.validacao_senha.domain.Senha;
import br.com.edson.validacao_senha.facade.SenhaFacade;
import br.com.edson.validacao_senha.security.Bucket4j;
import io.github.bucket4j.Bucket;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/v1/senha"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class SenhaController {

    @Autowired
    private SenhaFacade senhaFacade;

    private final Bucket bucketConfig = Bucket4j.bucketConfig();

    @PostMapping(value = "/validar_senha")
    public ResponseEntity<Boolean> isValid(@RequestBody @Valid final Senha senha, BindingResult validacaoSenha) {
        if (bucketConfig.tryConsume(1)) {
            return ResponseEntity.ok(senhaFacade.validarSenha(validacaoSenha));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}

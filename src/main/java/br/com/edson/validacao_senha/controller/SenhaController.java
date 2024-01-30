package br.com.edson.validacao_senha.controller;

import br.com.edson.validacao_senha.config.BucketConfig;
import br.com.edson.validacao_senha.domain.Senha;
import br.com.edson.validacao_senha.service.SenhaService;
import io.github.bucket4j.Bucket;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/v1/senha"})
public class SenhaController {
    private final SenhaService senhaService;
    private final Bucket bucketConfig = BucketConfig.setupBucket();

    public SenhaController(SenhaService senhaService) {
        this.senhaService = senhaService;
    }

    @PostMapping(value = "/validar_senha", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Boolean> isValid(@RequestBody @Valid Senha senha, BindingResult validacaoSenha) {
        if (bucketConfig.tryConsume(1)) {
            return ResponseEntity.ok(senhaService.validarSenha(validacaoSenha));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}

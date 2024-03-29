package br.com.edson.validacao_senha.controller;

import br.com.edson.validacao_senha.controller.domain.request.Senha;
import br.com.edson.validacao_senha.controller.domain.response.SenhaReponse;
import br.com.edson.validacao_senha.facade.SenhaFacade;
import br.com.edson.validacao_senha.security.HttpCallsLimit;
import io.github.bucket4j.Bucket;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = {"/v1/senha"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class SenhaController {

    public static final int NUM_TOKENS = 1;
    @Autowired
    private SenhaFacade senhaFacade;

    private final Bucket bucketConfig = HttpCallsLimit.bucketConfig();

    @PostMapping(value = "/validar_senha")
    public ResponseEntity<SenhaReponse> isValid(@RequestBody @Valid final Senha senha, final BindingResult validacaoSenha) {
        var correlationId = UUID.randomUUID().toString();

        log.info("Iniciando validação da senha do correlationId " + correlationId);

        return naoAntigiuLimiteResquests() ?
                ResponseEntity.ok(senhaFacade.validarSenha(validacaoSenha, correlationId)):
                ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    boolean naoAntigiuLimiteResquests() {
        return bucketConfig.tryConsume(NUM_TOKENS);
    }
}

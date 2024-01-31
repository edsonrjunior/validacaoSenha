package br.com.edson.validacao_senha.security;

import io.github.bucket4j.Bucket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HttpCallsLimitTest {

    @Test
    @DisplayName("Deve consumir no maximo 10 tokens")
    void deveConsumirNoMaximo10TokensPredefinidos() {
        final Bucket bucketConfig = HttpCallsLimit.bucketConfig();

        var QTDE_MAXIMA_TOKENS = 10;

        for (int i = 1; i <= QTDE_MAXIMA_TOKENS; i++) {
            assertTrue(bucketConfig.tryConsume(1));
        }
    }
}
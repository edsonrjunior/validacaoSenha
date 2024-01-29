package br.com.edson.validacao_senha.metrics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HealthCheckTest {

    @InjectMocks
    private HealthCheck healthCheck;
    @Test
    @DisplayName("Deve validar o status do health check")
    void deveVerificarOHealthCheck() throws Exception {
        Health.Builder builder = new Health.Builder();

        healthCheck.doHealthCheck(builder);
        Health health = builder.build();

        assertEquals(Status.UP, health.getStatus());
        assertEquals("WORKING", health.getDetails().get("app"));
    }
}
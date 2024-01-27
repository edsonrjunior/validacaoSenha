package br.com.edson.validacao_senha.metrics;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class HealthCheck extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(final Health.Builder builder) throws Exception {
        builder.up().withDetail("app", "WORKING");
    }
}

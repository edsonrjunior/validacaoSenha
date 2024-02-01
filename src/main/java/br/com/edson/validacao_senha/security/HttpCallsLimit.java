package br.com.edson.validacao_senha.security;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

public class HttpCallsLimit {
    private HttpCallsLimit() {}

    @Bean
    public static Bucket bucketConfig() {
        var refill = Refill.intervally(10, Duration.ofMinutes(1));
        var limit = Bandwidth.classic(10, refill);

        return Bucket.builder()
                .addLimit(limit)
                .build();
    }
}

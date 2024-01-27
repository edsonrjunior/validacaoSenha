package br.com.edson.validacao_senha.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API de validação de senha")
                        .description("Esta API valida os caracteres de uma senha para definir se é válida ou não, " +
                                "retornando true quando válida e false quando inválida.")
                        .version("v1.0.0")
                        .license(new License().name("Edson Raimundo Júnior").url("https://github.com/edsonrjunior")))
                .externalDocs(new ExternalDocumentation()
                        .description("Código fonte no Github")
                        .url("https://github.com/edsonrjunior/validacaoSenha/tree/master"));
    }
}

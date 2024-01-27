package br.com.edson.validacao_senha.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SwaggerConfigTest {

    @Test
    @DisplayName("Deve validar as configuracoes que sao exibidas no Swagger UI")
     void testSwaggerConfig() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        OpenAPI swagger = swaggerConfig.springShopOpenAPI();

        assertEquals("API de validação de senha", swagger.getInfo().getTitle());
        assertEquals("Esta API valida os caracteres de uma senha para definir se é válida ou não, retornando true quando válida e false quando inválida.", swagger.getInfo().getDescription());
        assertEquals("v1.0.0", swagger.getInfo().getVersion());

        Contact contact = swagger.getInfo().getContact();
        assertEquals("Edson Raimundo Júnior", contact.getName());
        assertEquals("https://www.linkedin.com/in/edsonrjunior/", contact.getUrl());

        ExternalDocumentation externalDocs = swagger.getExternalDocs();
        assertEquals("Código fonte", externalDocs.getDescription());
        assertEquals("https://github.com/edsonrjunior/validacaoSenha/tree/master", externalDocs.getUrl());
    }

}
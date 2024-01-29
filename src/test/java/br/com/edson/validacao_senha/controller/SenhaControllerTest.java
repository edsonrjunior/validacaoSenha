package br.com.edson.validacao_senha.controller;

import br.com.edson.validacao_senha.domain.Senha;
import br.com.edson.validacao_senha.service.SenhaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SenhaController.class)
class SenhaControllerTest {


    @Autowired
    private MockMvc mock;

    @MockBean
    private SenhaService senhaService;

    @Spy
    private BindingResult validacaoSenha;

    @Test
    @DisplayName("Deve retornar http code 200")
    void deveRetornar200QuandoApiForChamada() throws Exception {
        var senha = new Senha("AbTp9!fok");

        mock.perform(post("/v1/senha/validar_senha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(senha)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar http code 404")
    void deveRetornar404QuandoAUrlForInvalida() throws Exception {
        var senha = new Senha("AbTp9!fok");

        mock.perform(post("/v1/senha/urlInvalida")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(senha)))
                .andExpect(status().isNotFound());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}



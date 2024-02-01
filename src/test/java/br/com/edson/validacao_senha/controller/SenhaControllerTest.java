package br.com.edson.validacao_senha.controller;

import br.com.edson.validacao_senha.controller.domain.request.Senha;
import br.com.edson.validacao_senha.facade.SenhaFacade;
import br.com.edson.validacao_senha.security.HttpCallsLimit;
import br.com.edson.validacao_senha.service.SenhaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bucket4j.Bucket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SenhaController.class)
@AutoConfigureMockMvc
class SenhaControllerTest {
    @Autowired
    private MockMvc mock;

    @MockBean
    private SenhaService senhaService;

    @MockBean
    private SenhaFacade senhaFacade;

    @Spy
    private BindingResult validacaoSenha;

    @Spy
    private SenhaController senhaController;

    @Test
    @DisplayName("Deve retornar http code 200")
    void deveRetornar200QuandoApiForChamada() throws Exception {
        mock.perform(post("/v1/senha/validar_senha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new Senha("AbTp9!fok"))))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar http code 404")
    void deveRetornar404QuandoAUrlForInvalida() throws Exception {
        mock.perform(post("/v1/senha/urlInvalida")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new Senha("AbTp9!fok"))))
                .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve retornar true quando nao atingir o limite de requests")
    void deveRetornarTrueQuandoAtingirOLimiteDeRequests() {
        Bucket bucketConfig = HttpCallsLimit.bucketConfig();
        when(senhaController.naoAntigiuLimiteResquests()).thenReturn(true);
        assertTrue(senhaController.naoAntigiuLimiteResquests());
    }

    @Test
    @DisplayName("Deve retornar false quando nao atingir o limite de requests")
    void deveRetornarFalseQuandoNaoAtingirOLimiteDeRequests() {
        Bucket bucketConfig = HttpCallsLimit.bucketConfig();
        when(senhaController.naoAntigiuLimiteResquests()).thenReturn(false);
        assertFalse(senhaController.naoAntigiuLimiteResquests());
    }

}



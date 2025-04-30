package br.com.edson.validacao_senha.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceUtils {

    public static Object getObject(final String arquivo, final Class klazz){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Object value = null;

        try {
            value = objectMapper.readValue(getResource(arquivo), klazz);
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
        }
        return value;
    }

    private static String getResource(final String arquivo) {
        String text = null;

        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(arquivo)){
            text = (new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))).lines().collect(Collectors.joining("\n"));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return text;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

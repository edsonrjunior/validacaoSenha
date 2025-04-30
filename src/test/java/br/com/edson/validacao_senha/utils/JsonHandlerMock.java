package br.com.edson.validacao_senha.utils;

import br.com.edson.validacao_senha.controller.domain.request.Senha;

public class JsonHandlerMock {

    public static Senha getSenhaClassFromJson(){
        return (Senha) ResourceUtils.getObject("json/senha.json", Senha.class);
    }

}

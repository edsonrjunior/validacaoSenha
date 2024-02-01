<h1 align="center"> API Validacao de Senhas </h1> <br>

## Sumario

- [Introdução](#introdução)
- [Features](#features)
- [Testes](#testes)


## Sobre o Projeto
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![JUnit](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)

[//]: # (![Bucket4j])

Esta API tem o objetivo de validar uma senha no formato string e retornar um booleano. Ela retorna verdadeiro quando 
atender todos os requisitos e falso quando não atender.

Requisitos:

- Ter nove ou mais caracteres
- Ter ao menos 1 dígito
- Ter ao menos 1 letra minúscula
- Ter menos 1 letra maiúscula
- Não ter espaços em branco
- Ter menos 1 caractere especial
  -  São considerados válidos os caracteres especias !@#$%^&*()-+


## API
* Endpoint HTTP:
  - **POST** ``/v1/senha/validar_senha`` Valida a se senha atende os requisitos retornando no formato json ``true`` em caso positivo e ``false`` caso negativo.
  - Tanto em caso positivo quando negativo, a aplicação irá responder com o HTTP status ```200 OK```.

| Parâmetro | Tipo parâmetro | Tipo dado   | Obrigatório | Descrição                           |
|-----------|----------------|-------------|-------------|-------------------------------------|
| senha     | ?              | Senha.class | Sim         | Classe com a a senha a ser validada |
  

## Arquitetura da aplicação
A aplicação foi construída com o framework Spring Boot na versão 3.2.2,a mais recente até o momento, assim como o Java 21. 
<br>Utiliza a ``Arquitetura Onion``facilitando a manutenção pois cada camada está bem definida e organizada, permitindo a testabilidade com métodos pequenos e bem definidos e a confiabilidade pois fluxo é facilmente mapeável e testável

* Camadas:
- **Controller:** Responsável por lidar as requisições HTTP, definindo o endpoint ``/v1/senha/validar_senha``, o tipo do request e response e os HTTPs status code.




## Observability
Está utilizando a biblioteca ``micrometer-registry-prometheus``expondo no endpoint``http://localhost:8080/actuator/prometheus`` as métricas de saúde, da JVM e do Prometheus.
Tais configurações estão definidas no arquivo ``application.yml``.
<br>
<br>Quanto à rastreabilidade, a aplicação está logando os principais eventos com a implementação da biblioteca ``@Slf4``.

```java
 log.info("Iniciando validação da senha do correlationId " + correlationId);
 log.info("A senha do correlationId " + correlationId + " possui erros");
```
A aplicação também expõe o status do health check no endpoint ``http://localhost:8080/actuator/health``.


## Executando a aplicação



O primeiro passo é clonar o repositório do Github e entrar no diretório da aplicação:

    git clone https://github.com/edsonrjunior/validacaoSenha.git
    cd validacaoSenha

Após abrir na IDE, faça o build da aplicação executando o comando ``mvn clean install`` Verique se o build finalizou com sucesso.
![Img Sucesso Build](img_build_sucess.png)

<br>Após finalizado o build, inicie a aplicação com o comando ``mvn spring-boot:run`` e verifique se está utilizando a porta 8080.
![Img App Start Up](img_start_up_app.png)

Em um aplicativo de testes de API (Postman ou Insomnia), crie uma resquisição do tipo POST no formato json conforme abaixo. Utilize o endpoint ```http http://localhost:8080/v1/senha/validar_senha```. <br> A seguir clique em SEND.

![Img Postman Request](img_postman_resquest.png)

Caso a string informada atenda os requitos, retornará ```{"valid": true}``` caso contrário ```{"valid": false}```, ambas com HTTP Status Code ```200```.

![Img Postman Response true](img_postman_response.png)

![Img Postman Response False](img_postman_response_false.png)

Em caso de mais de 10 requisições em 1 minuto a aplicação responderá com o HTTP Status Code ```429 Too Many Requests```.

![Img Postman Response 429](img_postman_response_429.png)

Este é um __mecanismo de segurança__ implementado com a biblioteca ```bucket4j-core``` para evitar ataques de [__DDoS__](https://www.microsoft.com/pt-br/security/business/security-101/what-is-a-ddos-attack) e poder ser facilmente ajustado na classe ```HttpCallsLimit```.
<br>O primeiro parâmetro do método ```intervally``` informa quantas requisições podem ser realizadas e o segundo parâmetro informa a duração que no caso é de 1 minuto. O primeiro parâmetro do método ```classic``` informa a capacidade máxima de requisições que a API terá.
```java
    @Bean
    public static Bucket bucketConfig() {
        var refill = Refill.intervally(10, Duration.ofMinutes(1));
        var limit = Bandwidth.classic(10, refill);

        return Bucket.builder()
                .addLimit(limit)
                .build();
```


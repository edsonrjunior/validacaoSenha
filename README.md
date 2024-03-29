<h1 align="center"> API Validação de Senhas </h1> <br>

## Sumario

- [Sobre o Projeto](#sobre-o-projeto)
- [API](#api)
- [Requisitos](#requisitos)
- [Arquitetura da aplicação](#arquitetura-da-aplicação)
- [Observability](#observability)
- [Executando a aplicação](#executando-a-aplicação)
- [Segurança](#segurança)
- [Documentação](#documentação)
- [Docker](#docker)
- [Testes Unitários e de Integração](#testes-unitários-e-de-integração)
- [Contato](#contato)

## [Sobre o Projeto](#sobre-o-projeto)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![JUnit](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)

Esta API tem o objetivo de validar uma senha no formato string e retornar um booleano. Ela retorna verdadeiro quando
atender todos os requisitos e falso quando não atender.

Requisitos:

- Ter nove ou mais caracteres
- Ter ao menos 1 dígito
- Ter ao menos 1 letra minúscula
- Ter menos 1 letra maiúscula
- Não ter espaços em branco
- Ter menos 1 caractere especial
    - São considerados válidos os caracteres especiais !@#$%^&*()-+

## [API](#api)

* Endpoint HTTP:
    - **POST** ``/v1/senha/validar_senha`` Valida se senha atende os requisitos retornando no formato json ``true`` em
      caso positivo e ``false`` caso negativo.
    - Tanto em caso positivo quando negativo, a aplicação irá responder com o HTTP Status Code ```200 OK```.

| Parâmetro | Tipo parâmetro | Tipo dado   | Obrigatório | Descrição                           |
|-----------|----------------|-------------|-------------|-------------------------------------|
| Senha     | RequestBody    | Senha.class | Sim         | Classe com a a senha a ser validada |

## [Requisitos](#requisitos)

* Java 17 ou superior
* Maven
* [Postman](https://www.postman.com/) ou [Insomnia](https://insomnia.rest/products/insomnia) para executar testes
* __IMPORTANTE__: Para executar em ambiente corporativo, é necessários configurar o ``settings.xml`` para baixar as
  bibliotecas do Artifactory

## [Arquitetura da aplicação](#arquitetura-da-aplicação)

A aplicação baseia-se na arquitetura Onion, dividindo em diferentes camadas de implementação,
tais como a camada de domínio, de serviço e o core do projeto. Utiliza também alguns dos princípios do Solid,
com classes de responsabilidade única e o princípio da segregação de interfaces, permitindo que o código seja mais
enxuto.

O framework utilizado é o Spring Boot na versão 3.2.2 com Java 21.

### Spring Validation

A regra de negócio responsável por validar a senha informada utiliza a
biblioteca [Spring Validation](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)
pois é de fácil utilização, disponibilizando diversas anotações que abstraem a implementação de algoritmos de validação.

```java

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Senha {

    private static final String REGEX_SENHA =
            "^(?!.*(.).*\\1)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+-])(?!.*[^a-zA-Z0-9!@#$%^&*()+-]+).{9,}$";

    @NotNull
    @Pattern(regexp = REGEX_SENHA)
    String password;

    public static boolean isRegexValido(String senha) {
        return senha.matches(REGEX_SENHA);
    }
```

A anotação ``@NotNull`` valida se a senha informada está nula retornando ``false`` na validação. <br>
A anotação ``@Pattern(regexp = * )`` é o coração da regra de validação, ela permite de forma simples fazer todas as
validações eliminando o uso de extensos ifs e elses.

```java
    /**
 * (?!.*(.).*\1)                    - Não permitir caracteres duplicados
 * (?!.*\s)                         - Não permitir espaços em branco
 * (?=.*\d)                         - Permitir dígitos
 * (?=.*[a-z])                      - Permitir letras minúsculas
 * (?=.*[A-Z])                      - Permitir letras maiúsculas
 * (?=.*[!@#$%^&*()+-])             - Permitir as caracteres especiais
 * (?!.*[^a-zA-Z0-9!@#$%^&*()+-]+)  - Negativa para não permitir nenhum caractere especial diferente da lista
 * .{9,}                            - Permitir que a senha tenha ao menos 9 caracteres
 */
```

* BindResult - Os resultados dos Spring Validation são retornados nesta classe e verificação pode ser verificada no
  método ``hasErrors()`` que retorna um boolean quando há erros na validação.

```java
@PostMapping(value = "/validar_senha")
public ResponseEntity<SenhaReponse> isValid(@RequestBody @Valid final Senha senha,final BindingResult validacaoSenha){
        var correlationId=UUID.randomUUID().toString();

        log.info("Iniciando validação da senha do correlationId "+correlationId);

        return naoAntigiuLimiteResquests()?
        ResponseEntity.ok(senhaFacade.validarSenha(validacaoSenha,correlationId)):
        ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
}
```
Utilização do métodos hasErrors()

```java
@Slf4j
@Service
public class SenhaServiceImpl implements SenhaService {
    @Override
    public SenhaReponse validarSenha(final BindingResult validacaoSenha, final String correlationId) {
        if (validacaoSenha.hasErrors()) {
            log.info("A senha do correlationId " + correlationId + " possui erros");
            return new SenhaReponse(false);
        } else {
            log.info("A senha do correlationId " + correlationId + " não possui erros");
            return new SenhaReponse(true);
        }
    }
}
```

### Lombok
Em diversos trechos é utilizado o Lombok para simplificar o código, eliminando a necessidade de construir vários 
boiler plates como getter, setters e construtores.


## [Observability](#observability)

Utiliza a biblioteca ``micrometer-registry-prometheus``
expondo no endpoint``http://localhost:8080/actuator/prometheus`` as métricas de saúde, da JVM e do Prometheus, tais
métricas podem posteriormente serem capturadas e utilizadas pelo [Grafana](https://grafana.com/) para criação de
dashboards.
Tais configurações estão definidas no arquivo ``application.yml``.

A aplicação utiliza a biblioteca ``Slf4`` para logar os principais eventos.

```java
log.info("Iniciando validação da senha do correlationId "+correlationId);
log.info("A senha do correlationId "+correlationId+" possui erros");
```

Também expõe o status do health check no endpoint ``http://localhost:8080/actuator/health``.

## [Executando a aplicação](#executando-a-aplicação)

O primeiro passo é clonar o repositório do Github e entrar no diretório da aplicação:

```shell
git clone https://github.com/edsonrjunior/validacaoSenha.git
cd validacaoSenha
```

Após abrir na IDE, faça o build da aplicação executando o comando ``mvn clean install`` e verifique se o build finalizou
com sucesso.
![Img Sucesso Build](doc/img_build_sucess.png)

<br>Após finalizado o build, inicie a aplicação com o comando ``mvn spring-boot:run`` e verifique se está utilizando
a porta 8080.
![Img App Start Up](doc/img_start_up_app.png)

Em um aplicativo de testes de API (Postman ou Insomnia), crie uma resquisição do tipo POST no formato json conforme
abaixo. Utilize o endpoint ```http://localhost:8080/v1/senha/validar_senha```. <br> A seguir clique em SEND.

![Img Postman Request](doc/img_postman_resquest.png)

Caso a string informada atenda os requisitos, retornará ```{"valid": true}``` caso contrário ```{"valid": false}```,
ambas com HTTP Status Code ```200```.

![Img Postman Response true](doc/img_postman_response.png)

![Img Postman Response False](doc/img_postman_response_false.png)

Em caso de mais de 10 requisições em 1 minuto a aplicação responderá com o HTTP Status Code ```429 Too Many Requests```.

![Img Postman Response 429](doc/img_postman_response_429.png)

## [Segurança](#segurança)

Limitar a quantidade de requisições é um __mecanismo de segurança__ implementado com a biblioteca ```bucket4j-core```
para proteger contra ataques de
[__DDoS__](https://www.microsoft.com/pt-br/security/business/security-101/what-is-a-ddos-attack) e pode
ser facilmente parametrizado na classe ```HttpCallsLimit```.
<br><br>O primeiro parâmetro do método ```intervally``` informa quantas requisições podem ser realizadas e o segundo
parâmetro informa a duração que no caso é de 1 minuto. O primeiro parâmetro do método ```classic``` informa a
capacidade máxima de requisições que a API terá.

```java
@Bean
public static Bucket bucketConfig(){
    final var refill=Refill.intervally(10,Duration.ofMinutes(1));
    final var limit=Bandwidth.classic(10,refill);

    return Bucket.builder()
        .addLimit(limit)
        .build();
}
```
Outra implementação utilizada é sempre proteger as classes com ``private`` e objetos com ``final`` contra modificação em tempo de execução ou mesmo 
de implementação indevida.

## [Documentação](#documentação)

A aplicação utiliza o [Swagger](https://swagger.io/) que permite documentar de forma fácil e visual demonstrando
quais parâmetros necessários no request como também o response. Permite também executar testes de forma fácil.
Endpoint: ``http://localhost:8080/swagger-ui/index.html#/``

![Img Sucesso Build](doc/img_swagger.png)

## [Docker](#docker)

E possível também executar facilmente com o Docker

Crie a imagem docker com o comando abaixo

```shell 
docker build -t api/validacao_senha .
```

Em seguida crie o container para que a peça possa iniciar a execução

```shell 
docker run -d -p 8080:8080 --name valida_senha api/validacao_senha
```

Então verifique se o container foi criado corretamente.

```shell 
docker container ps
```

![Img Cmd Docker Ps](doc/img_cmd_docker_ps.png)

Depois, basta executar os testes como descritos na sessão [Executando a aplicação](#executando-a-aplicação).

## [Testes Unitários e de Integração](#testes-unitários-e-de-integração)

A implementação utiliza o framework JUnit 5 com as bibliotecas Mockito e WebMvc.
Possui 23 testes unitários com 100% das classes, 91.7% dos métodos e 92% das linhas cobertas.

![Img Code Coverage](doc/img_code_coverage.png)

## [Contato](#contato)

Em caso de dúvida ou sugestões entre em contato nos canais de sua preferência

- [Email](mailto:edsonkjr@gmail.com)
- [LinkedIn](https://www.linkedin.com/in/edsonrjunior/)
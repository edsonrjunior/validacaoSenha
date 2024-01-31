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

Esta API tem o objetivo de validar uma senha no formato string e retornar um booleano. Retornar verdadeiro quando 
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
  - **POST** ``/v1/senha/validar_senha`` Valida a se senha atende os requisitos retornando ```{"valid": true}``` em caso positivo e ```{"valid": false}``` caso negativo.
  - Tanto em caso positivo quando negativo, a aplicação irá responder com o HTTP status ```200 OK```.
  - Em casos de mais de 100 requisições em 1 minuto a aplicação responderá com o HTTP status ```429 Too Many Requests```'

| Parâmetro | Tipo parâmetro | Tipo dado   | Obrigatório | Descrição                           |
|-----------|----------------|-------------|-------------|-------------------------------------|
| senha     | ?              | Senha.class | Sim         | Classe com a a senha a ser validada |
  

## Arquitetura da aplicação
A aplicação foi construída com o framework Spring Boot na versão ``3.2.2``,a mais recente até o momento, assim como o Java ``21``. 
<br>Utiliza a ``Arquitetura Onion``facilitando a manutenção pois cada camada está bem definida e organizada, permitindo a testabilidade com métodos pequenos e bem definidos e a confiabilidade pois fluxo é facilmente mapeável e testável

* Camadas:
- **Controller:** Responsável por lidar as requisições HTTP, definindo o endpoint ``/v1/senha/validar_senha``, o tipo do request e response e os HTTPs status code.




## Observability
Está utilizando a biblioteca ``micrometer-registry-prometheus``expondo no endpoint``http://localhost:8080/actuator/prometheus`` as métricas de saúde, da JVM e do Prometheus.
Tais configurações estão definidas no arquivo ``application.yml``.
<br>
<br>Quanto à rastreabilidade, a aplicação está logando os principais eventos com a implementação da biblioteca ``@Slf4``.
A aplicação também expõe o status do health check no endpoint ``http://localhost:8080/actuator/health``.


## Executando a aplicação



O primeiro passo é clonar o repositório do Github e entrar no diretório da aplicação:

    git clone https://github.com/edsonrjunior/validacaoSenha.git
    cd validacaoSenha

Após abrir na IDE, faça o build da aplicação executando o comando ``mvn clean install``. Após finalizado o build, inicie a aplicação com o comando ``mvn spring-boot:run``.

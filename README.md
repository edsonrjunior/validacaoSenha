<h1 align="center"> API Validacao de Senhas </h1> <br>

## Sumario

- [Introdução](#introdução)
- [Features](#features)
- [Testes](#testes)


## Introdução
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


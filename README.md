# 🐾 Sistema de Cadastro de Pets - Abrigo

Este projeto é uma API REST desenvolvida em **Spring Boot** para gerir o fluxo de cadastro de animais (Pets) e adotantes num abrigo. A aplicação inclui integrações externas para validação de endereços e uma estrutura robusta de base de dados e testes.

---

## Tecnologias e Ferramentas

### Backend
- Java 17 (LTS)
- Spring Boot 3.3.0
  - Spring Data JPA
  - Spring Validation
  - OpenFeign (integração com ViaCep)

### Banco de Dados
- PostgreSQL
- Flyway (versionamento de schema)
  - `src/main/resources/db/migration`

### Outras Ferramentas
- Lombok
- Docker
- Docker Compose
- JUnit 5
- Mockito
- Maven

---

## Arquitetura do Projeto

A aplicação segue uma arquitetura em camadas:

1. **Controller**  
   Responsável pela exposição dos endpoints REST  
   - `AdotanteController`
   - `PetController`

2. **Service**  
   Contém a lógica de negócio e validações

3. **Repository**  
   Comunicação com o banco de dados via JPA

4. **DTO (Data Transfer Object)**  
   Transporte de dados entre as camadas

5. **Model**  
   Entidades que representam as tabelas do banco de dados

---

## Como Executar o Projeto

### Pré-requisitos
- Java 17
- Maven 3.x
- Docker e Docker Compose

---

### Passo a Passo

#### 1. **Subir o banco de dados com Docker**:

No diretório raiz do projeto, execute:

`docker-compose up -d`

#### 2. **Executar a Aplicação**:
 
 `mvn spring-boot:run`

#### 3. **Acessar API**:
A API estará disponível por padrão em:

 `http://localhost:8080`

 ### Testes Unitários

 Para executar os testes:

 `mvn test`

  ### Coleção de Requisições

Exemplos de requisições e coleções estão disponíveis nos seguintes diretórios:

`postman/`
Coleção exportada para Postman ou Insomnia

`json/`
Exemplos de payloads para criação de Pets e Adotantes

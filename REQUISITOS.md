### DESAFIO CADASTRO PET V2 ###

### desafio 


### requisitos ###
## Parte 1 ##
[x] Gerar projeto (Spring Initializr)

[x] Configurar banco de dados (Docker Compose)

[x] Criar Entidade Pet (JPA e Validation)

[x] Criar Enums

[x] Criar PetRepository

[x] Criar métodos customizados de busca

[x] Lógica de Cadastro (Validações)

[x] Lógica de Busca e Filtro 

[x] Implementar Endpoints (POST, GET, PUT, DELETE)


## Parte 2 ##
[x] Você deverá implementar a funcionalidade de MIGRATIONS no banco de dados, para isso, você deve usar alguma ferramenta de migrations a sua escolha (ex: Flyway ou Liquibase).

[x] Voce deverá garantir que todos os scripts SQL estejam organizados em um diretório chamado /migrations, ou seja, todas as relações de banco de dados e querys executadas, com isso, você deverá criar cada uma delas para cada entidade.

[x] Você deverá criar migrations específicas para popular o banco com 3 registros de teste para cada entidade (ex: testPet1, testPet2, testPet3)

[x] Implementar o CRUD completo (Create, Read, Update, Delete) seguindo a estrutura definida nas migrations.

[x] Implementar a entidade Adotante com base na migrations

[x] Implementar a entidade Endereco que deverá ser associada a entidade Adotante, ou seja, todo adotante, tem um Endereco associado, crie as validações necessarias.

[ ] Criar os Repositories e DTO's para Endereco e Adotante

[ ] EXTRA -> Pedir somente o CEP e o numero da casa para preencher alguns campos de campo endereço, procurar como consumir API usando FeignClient
 
## Parte 3 ##
[ ] Você deverá usar o POSTMAN, criando uma collection + environment com todos os endpoints utilizados na aplicação. 

[ ] Você deverá implementar a funcionalidade de DOCUMENTAÇÃO, utilizando o Swagger URL: http://localhost:8080/swagger-ui.html site do swagger: https://swagger.io/

[ ] Você deverá implementar testes UNITÁRIOS NA APLICAÇÃO, para isso, você deve usar o JUnit 5 e Mockito, garantindo um coverage de 80%

## Parte 4 ## 
[ ] Você deverá implementar Autenticação com tokens JWT, os endpoints de POST de pets, só poderão ser criados como uma certa autorização, ele tem que ter um header / role de admin.

## Parte 5 ##
[ ] Você deverá implementar os testes de integração na aplicação, ou seja, deverá executar a aplicação e verificar se os endpoints estão funcionando corretamente, rodando uma SEQUENCIA DE CHAMADAS.

## Parte 6 ##
[ ] Você deverá implementar a funcionalidade de LOGS, utilizando o Logback, url: https://logback.qos.ch/


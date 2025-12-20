### DESAFIO CADASTRO PET V2 ###

### desafio 


### requisitos ###
## Parte 1 ##
Você vai escrever o que já foi tudo feito.

## Parte 2 ##
[ ] Você deverá implementar a funcionalidade de MIGRATIONS No banco de dados, para isso, você deve usar alguma ferramenta de migrations a sua escolha.

[ ] Você deverá nas migrations, colocar pelo menos 3 entidades de cada de teste, com nomeclaturas de teste, exemplo testPet1 - testPet2 - testPet3 - PARA CADA ENTIDADE

[ ] Devera haver um diretório chamado /migrations, com todas as relações de banco de dados e querys executadas, com isso, você deverá criar cada uma delas para cada entidade.

[ ] Implementar o CRUD básico de Pets com base na migrations

[ ] Implementar a entidade Adotante com base na migrations

[ ] Implementar a entidade ContatoEEndereco que deverá ser associada a entidade Adotante, ou seja, todo adotante, tem um ContatoEEndereco associado, crie as validações necessarias.

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


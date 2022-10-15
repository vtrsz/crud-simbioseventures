# <p align="center">CRUD Application for Simbiose Ventures</p>

## Tecnologias Utilizadas:

- Java 17
- PostgreSQL
- Spring Boot
- Maven
- JUnit
- Mockito
- Lombok

## Endpoints

- [x] <b>[POST] /pessoa</b> - cadastra uma nova pessoa
- [x] <b>[GET] /pessoa/{id}</b> - retorna os dados de uma pessoa em específico
- [x] <b>[PUT] /pessoa/{id}</b> - altera os dados de uma pessoa em específico
- [x] <b>[DELETE] /pessoa/{id}</b> - deleta os dados de uma pessoa em específico
- [x] <b>[GET] /pessoas</b> - retorna uma lista de pessoas cadastradas

### Exemplo de requests:

##### /pessoa POST:
```json
{ "name": "John Doe", "email": "john@doe.com", "birthDate": "2000-01-01" }
```
##### /pessoa/1 PUT:
```json
{"name": "John Moe", "email": "john@moe.com", "birthDate": "2001-01-01" }
```

## Como rodar o projeto?

Baixe o <a href="https://www.postgresql.org/download/">PostgreSQL</a> <br>

Crie uma database para a aplicação normal e uma para o ambiente de testes.

### Configuração do Banco de Dados

No arquivo <b>application.properties</b> <i>(localizado no "src/main/resources")</i>

```properties
spring.datasource.url=jdbc:postgresql://<HOSTNAME>:<PORTA DO BANCO DE DADOS>/<NOME DA DATABASE/SCHEMA>
spring.datasource.username=<USUARIO>
spring.datasource.password=<SENHA>
```

#### Exemplo:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/simbioseventures_crud
spring.datasource.username=postgres
spring.datasource.password=123
```

onde localhost é o hostname, 5432 a porta do banco de dados, e simbioseventures_crus o nome da database.

### Configuração do Banco de Dados de Teste
No arquivo <b>test.properties</b> <i>(localizado no "src/test/resources")</i>
Segue o mesmo padrão do application.properties.

#### Exemplo:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/simbioseventures_crud_test
spring.datasource.username=postgres
spring.datasource.password=123
```
<i><b>Obs: necessário que seja uma database/schema diferente do ambiente principal da aplicação!</b></i>

# Sgcapi

Projeto da Disciplina DCC208 A - Desenvolvimento Web BACK-END.
O projeto busca implementar uma API para um Sistema de Gerenciamento de Concessionária.
Desenvolvido com Spring Boot. O arquivo `documentacao_req_uml.zip` possui o Diagrama de Classes e O Documento de Requisitos da API.

### Dependências:

- Docker, PostgreSQL;
- PGadmin, Postman;
- Java 21, Maven 3.8.7;


### Endereços da aplicação
- Swagger UI: http://localhost:8080/swagger-ui/index.html#/
- pgadmin = http://localhost:5050/browser/
- postman = http://localhost:8080/api/v1/{path}




### Comandos
```bash
sudo docker compose up
./mvnw spring-boot:run (Unix)
```



### Configuração do Docker File

```
// docker-compose.yml
services:
  postgres:
    image: postgres:16
    container_name: spring-postgres
    restart: always
    environment:
      POSTGRES_DB: sgcapi_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  pgadmin:
    image: dpage/pgadmin4
    container_name: spring-pgadmin
    restart: always
    environment:
      PGADMIN_DEFAULT_EMAIL: admin@admin.com
      PGADMIN_DEFAULT_PASSWORD: admin
    ports:
      - "5050:80"
    depends_on:
      - postgres

volumes:
  postgres_data:
```

### Configuração do application.properties
```
/// appication.properties
spring.application.name=sgcapi
#spring.datasource.url=jdbc:postgresql://172.18.10.31:5433/
spring.datasource.url=jdbc:postgresql://localhost:5432/sgcapi_db
spring.datasource.username=postgres
#spring.datasource.password=System
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
spring.datasource.properties.serverTimezone=UTC

security.jwt.expiracao=30
security.jwt.chave-assinatura=YXBpIHNpc3RlbWEgZGUgY29udHJvbGUgYWNhZGVtaWNv

```


### MÉTODOS DE EXEMPLO DO CRUD Funcionario

#### Exemplo token
- Autenticação POST http://localhost:8080/api/v1/funcionarios/auth
```json
{
  "login": "rodolfo@gmail.com",
  "senha": "123"
}
``` 
- GET http://localhost:8080/api/v1/funcionarios (READ)
- POST http://localhost:8080/api/v1/funcionarios (CREATE)
```json
{
  "nome": "Alberto",
  "email": "alberto@gmail.com",
  "endereco": "Rua Outubro",
  "numTelefone": "0214445569",
  "login": "alberto@gmail.com",
  "senha": "123456",
  "senhaRepeticao": "123456",
  "cpf": "01234567891",
  "admin": true
}

```
- PUT http://localhost:8080/api/v1/funcionarios/{id} (UPDATE)
- 
  ```json 
  {
    "nome": "Alberto",
    "email": "alberto@gmail.com",
    "endereco": "Rua Outubro",
    "numTelefone": "0214445569",
    "login": "alberto@gmail.com",
    "senha": "123456",
    "senhaRepeticao": "123456",
    "cpf": "01234567891",
    "admin": true
  }
  ```

- DELETE http://localhost:8080/api/v1/funcionarios/{id} (DELETE)

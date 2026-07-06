# Informações Relevantes para o sgcapi

Projeto da Disciplina DCC208 A - Desenvolvimento Web BACK-END.
O projeto busca implementar uma API para um Sistema de Gerenciamento de Concessionária.
Desenvolvido com Spring Boot.

### Dependências:

Docker, PostgreSQL;
PGadmin, Postman;
Java 21, Maven 3.8.7;


### Endereços da aplicação
Swagger ui: http://localhost:8080/swagger-ui/index.html#/
pgadmin = http://localhost:5050/browser/
postman = http://localhost:8080/api/v1/{path}




### Comandos
sudo docker compose up
./mvnw spring-boot:run (Linux)



### Configuração do Docker Arquivos


/// docker-compose.yml
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

/// FIM_DO_ARQUIVO

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

// FIM_DO_ARQUIVO


### MÉTODOS DE EXEMPLO DO CRUD Funcionario

GET http://localhost:8080/api/v1/funcionarios (READ)
POST http://localhost:8080/api/v1/funcionarios (CREATE - criar novo funcionario)
  {
    "nome": "Claudemar",
    "email": "lucario@rocks.com",
    "login": "lucario@rocks.com",
    "endereco": "Cidade de Pallet",
    "numTelefone": "callmeonmy",
    "senha": "1103",
    "senhaRepeticao": "1103",
    "cpf": "celokochefe-#448",
    "admin": true
  }
POST http://localhost:8080/api/v1/funcionarios/auth (Autenticação/Segurança)
  {
    "login": "lucario@rocks.com",
    "senha": "1103"
  }
PUT http://localhost:8080/api/v1/funcionarios/{id} (UPDATE)
  {
    "nome": "Claudemar",
    "email": "lucario@rocks.com",
    "login": "lucario@rocks.com",
    "endereco": "Cidade de Pallet",
    "numTelefone": "callmeonmy",
    "senha": "1103",
    "senhaRepeticao": "1103",
    "cpf": "celokochefe-#448",
    "admin": true
  }

DELETE http://localhost:8080/api/v1/funcionarios/{id} (DELETE)

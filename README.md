# PROJETO CAPACITAÇÃO SPRING BOOT

## Objetivo do projeto:
O objetivo do projeto é desenvolver uma aplicação utilizando o framework Spring Boot, com foco na capacitação e aprendizado. O projeto visa criar um sistema robusto e escalável que gerencie pedidos, produtos e usuários, além de implementar funcionalidades como:
notificações por e-mail, autenticação de usuários e persistência de dados em um banco de dados relacional.

## 🛠 Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3**
* **Spring Security** (PasswordEncoder)
* **JWT** (geração de token)
* **Spring Data JPA**
* **PostgreSQL** (Produção) / **H2** (Testes)
* **Lombok**

## 🏃‍♂️ Como Rodar o Projeto

Para utilizar este projeto, siga os passos abaixo:

1. **Clonar o repositório**:
```bash
  git clone git@github.com:LoriJr/CapacitacaoSpringBootAPI.git
```

2. ## ⚙️ Configuração (application.properties)

Para rodar o projeto localmente, certifique-se de que seu arquivo `src/main/resources/application.properties` contenha as seguintes configurações.

```properties
# Configuração do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/db_capacitacao
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=postgres

# JPA / Hibernate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
```
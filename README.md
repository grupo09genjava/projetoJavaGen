# Projeto API Java Generation Curso AWS

Este projeto é sobre um gerenciador desenvolvido para auxilar quem usa em seus registros de alunos, turmas e funcionários.

## Sumário

- [Visão Geral](#generalview)
- [Decisões de Tecnologia](#achitecturedecisions)
- [Tecnologias](#technologies)
- [Estrutura do Projeto](#projectstructure)
- [Instalação](#installation)
- [Como Usar](#howtouse)

## Visão Geral

Foi desenvolvido para fornecer um serviço back-end robusto e modular, utilizando tecnologias como Java, Spring Boot, PostgreSQL, Spring Security e Docker, facilitando a manutenção e evolução da aplicação.

## Documentação

   ```bash
   https://projetojavagen-7qgg.onrender.com/swagger-ui/index.html#/
   ```
Foi desenvolvido para fornecer um serviço back-end robusto e modular, utilizando tecnologias como Java, Spring Boot, PostgreSQL, Spring Security e Docker, facilitando a manutenção e evolução da aplicação.

## Decisões de Tecologias

- Java: Ele se destaca como uma opção confiável para APIs básicas devido ao seu ecossistema avançado com Spring Boot, que proporciona alta produtividade, segurança e simplicidade de manutenção, além de possuir uma vasta documentação e uma ampla comunidade de programadores.
- Spring Boot: Spring Boot facilita a criação de aplicações Java ao eliminar configurações complexas, disponibilizar funcionalidades prontas para uso (como servidor integrado e autoconfiguração) e possibilita a criação rápida de APIs REST com um mínimo de código.
- Spring Security: É um poderoso framework que incorpora autenticação e permissão em aplicações Java, tornando mais fácil a implementação de login, controle de acesso e defesa contra vulnerabilidades habituais, através de configurações de segurança padronizadas.
- Docker: Utilizado para containerizar tanto o banco de dados PostgreSQL quanto a aplicação Java. O Dockerfile é configurado para buildar a imagem da aplicação Java, permitindo que a aplicação seja executada dentro de um container, garantindo um ambiente de desenvolvimento e produção consistente.

## Tecnologias

- [Java](https://www.oracle.com/br/java/technologies/downloads/) - Liguagem utilizada para desenvolvimento da API.
- [Spring Boot](https://start.spring.io) - Framework utilizado para apoio ao desenvolvimento da API.
- [Swagger](https://springdoc.org) - Documentação dos Endpoints da API.
- [Docker](https://www.docker.com/) - Plataforma para desenvolvimento, envio e execução de aplicações em containers.
- [PostgreSQL](https://www.postgresql.org/) - Sistema de gerenciamento de banco de dados relacional.

## Estrutura do Projeto

A estrutura do projeto é organizada em camadas para garantir a separação de responsabilidades:

- **Config:**  
  Contém as configuração do Swagger e Spring Security.

- **Controller:**  
  Contém os Endpoints para os acessos das requisições do Cliente HTTP e tratamento através de DTO de entrada de dados.

- **DTO:**  
  Responsável pela conversão e tráfego de dados de entrada e saida da API.

- **Exception:**  
  Responsável pelo tratamento de excessões das requisições HTTP.

- **Handler:**  
  Responsavel pelo tratamento global das excessões HTTP.

- **Model:**  
  Contém a extrutura de Modelos de entidades representados do Banco de Dados.

- **Repository:**  
  Mecanismo responsável pela persistencia da entidade agregadora.

- **Security:**  
  Contém as configurações de segurança da API.

- **Service:**  
  Responsável pela execução da lógica de negócios da API.

- **Utils:**  
  Responsável pelas ferramentas auxiliares para funcionamento da aplicação.


### Pré-requisitos

- JDK (versão 17.X.X)
- Docker (versão 4.34.X)
- PostgreSQL (versão 16.X.X)
- Spring Boot (versão 3.3.4)
- Maven (versão 4.X.X)

### Passos para Instalar

1. Clone este repositório:
   ```bash
   git clone https://github.com/grupo09genjava/projetoJavaGen.git
   ```
2. Entre na pasta do projeto:

   ```bash
   cd nome-do-projeto
   ```

3. Instale as dependências:

   ```bash
   mvn clean install
   ```

4. Configure as variáveis de ambiente:

   ```bash
   cp .env.example .env
   # Edite o arquivo .env com suas configurações
   ```

5. Inicie os containers do Docker:

   ```bash
   docker compose --env-file .env up -d
   ```

## Como Usar

1. Inicie o servidor de desenvolvimento:

   ```bash
   mvn spring-boot:run
   ```

2. Abra o navegador e acesse [http://localhost:8080](http://localhost:8080/swagger-ui/index.html).

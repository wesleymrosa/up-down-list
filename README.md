# Up Down List API

## Visão Geral

A Up Down List API é um sistema desenvolvido em Java com Spring Boot 3.3.1, projetado para gerenciar arquivos do tipo .pdf, permitindo operações de upload, download e organização de documentos dentro de um repositório estruturado.

Este projeto utiliza o PostgreSQL como banco de dados para armazenar as informações dos arquivos de maneira eficiente.

## Tecnologias Utilizadas

- Back-end:

- Java 17 - Linguagem de programação principal.

- Spring Boot 3.3.1 - Framework para desenvolvimento da aplicação.

- Spring Boot Starter Web - Para exposição de endpoints REST.

- Spring Boot Starter Data JPA - Para manipulação de dados via ORM Hibernate.

- Spring Cloud OpenFeign - Para consumo de APIs externas de forma declarativa.

- Lombok - Para redução de boilerplate no código Java.

- Spring Boot Actuator - Para monitoramento da aplicação.

- Dotenv - Para gestão de variáveis de ambiente.

- Springdoc OpenAPI - Para documentação da API via Swagger.

## Banco de Dados:

- PostgreSQL - Banco de dados relacional padrão.

## Ferramentas e Gerenciamento:

- Maven - Para gestão de dependências e build do projeto.

- Postman - Para testes dos endpoints.

- Swagger UI - Interface interativa para testar a API.

- cURL - Testes via linha de comando.

## Como Configurar e Executar o Projeto

1. Clonar o Repositório

Para clonar o repositório, escolha uma das opções abaixo:

🔹 Via HTTPS:
````

git clone https://github.com/wesleymrosa/up-down-list.git
````
🔹 Via SSH:
````
git clone git@github.com:wesleymrosa/up-down-list.git
````
🔹 Via GitHub CLI:
````
gh repo clone wesleymrosa/up-down-list
````
Após clonar, acesse a pasta do projeto:
````
cd up-down-list
````
## 2. Abrir no IntelliJ IDEA

 - Abra o IntelliJ IDEA.

- Clique em File > Open e selecione a pasta up-down-list.

- Aguarde o IntelliJ carregar o projeto e baixar as dependências do Maven.

- Verifique se o SDK Java 17 está configurado corretamente (File > Project Structure > SDKs).

## 3. Configurar o Banco de Dados PostgreSQL

Crie um banco de dados chamado up_down_list_db.

Configure as credenciais no arquivo .env na raiz do projeto:
````
DB_URL=jdbc:postgresql://localhost:5432/up_down_list_db
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
````
## 4. Compilar e Construir o Projeto

No terminal, dentro da pasta do projeto, execute:
````
mvn clean install
````
5. Executar a Aplicação

Após compilar o projeto, execute:
````
mvn spring-boot:run
````
Se tudo estiver correto, a aplicação será iniciada na porta 8080.

## Como Testar a API

6. Via Postman

Abra o Postman.

Crie uma nova requisição GET com a URL:
````
http://localhost:8080/arquivos
````
Clique em Send para visualizar os dados.

7. Via Swagger UI

Acesse a documentação interativa no navegador:
````
http://localhost:8080/swagger-ui.html
````
8. Via cURL (Prompt de Comando)

Execute:
````
curl -X GET http://localhost:8080/arquivos
````
9. Via Navegador

Digite a seguinte URL no navegador:
````
http://localhost:8080/arquivos
````
A resposta será exibida em formato JSON.

## Estrutura do projeto

A estrutura do projeto Up Down List API segue uma abordagem modular e bem definida, baseada no framework Spring Boot. 
Cada diretório possui um propósito específico, garantindo organização, escalabilidade e facilidade na manutenção. 

A seguir, é detalhada a estrutura dos diretórios e seus respectivos papéis dentro da aplicação.

### - Estrutura de Diretórios e Funções
### Diretório src/main/java/com/example/updownlist
Este diretório contém toda a implementação do código-fonte da aplicação, estruturada seguindo boas práticas de desenvolvimento e padrões arquiteturais, como Controller-Service-Repository.

### - controller/
Diretório responsável por conter as classes que expõem os endpoints REST da aplicação. As classes dentro desta pasta utilizam anotações do Spring MVC, como @RestController e @RequestMapping, permitindo que a API receba requisições HTTP e responda adequadamente.

### - service/
Camada intermediária entre os controladores e a camada de acesso ao banco de dados. Aqui são implementadas as regras de negócio e a lógica de processamento das operações de manipulação de arquivos .pdf.

### - repository/
Contém as interfaces que fazem o acesso ao banco de dados utilizando Spring Data JPA. Essas interfaces herdam de JpaRepository, permitindo operações básicas de persistência (CRUD) sem a necessidade de implementação manual de queries.

### - model/
Armazena as classes que representam as entidades do banco de dados. Essas classes são anotadas com @Entity, indicando ao Hibernate que devem ser mapeadas para tabelas no banco de dados.

### - dto/
Diretório que contém os Data Transfer Objects (DTOs), utilizados para transferir dados entre o frontend, backend e serviços externos. O uso de DTOs evita o acoplamento direto entre as entidades do banco e as respostas da API.

### - config/
Contém classes responsáveis pelas configurações do projeto, como:

- Configuração do Swagger/OpenAPI para documentação da API.
- Configuração de variáveis de ambiente e segurança.
- Configurações específicas do Spring Boot.

### - mapper/
Responsável por converter entidades em DTOs e vice-versa. Utiliza ferramentas como MapStruct para facilitar e automatizar esse processo.

### - Diretório src/main/resources/
Contém arquivos de configuração e recursos necessários para a execução da aplicação.

### - application.properties
Arquivo principal de configuração do Spring Boot, onde são definidas:

- Configurações de conexão com o banco de dados.
- Configuração de portas e logs da aplicação.
- Definições do Spring Boot Actuator para monitoramento.

 ### - .env
 Arquivo opcional que contém variáveis de ambiente sensíveis, como credenciais de acesso ao banco de dados. É recomendado manter esse arquivo fora do versionamento do Git para evitar exposição de dados sensíveis.

### - Arquivos no Diretório Raiz
pom.xml
Arquivo de configuração do Maven, utilizado para gerenciar as dependências e o ciclo de vida do projeto. Contém todas as bibliotecas utilizadas, incluindo Spring Boot, JPA, Lombok, Feign Client, entre outras.

### - README.md
Arquivo de documentação do projeto, fornecendo instruções detalhadas sobre instalação, configuração e uso da API.

## Conclusão
A Up Down List API foi projetada com uma arquitetura modular e escalável, seguindo os princípios recomendados para aplicações Java baseadas em Spring Boot. A separação entre Controller, Service, Repository, Model e DTO promove uma organização eficiente do código, garantindo baixo acoplamento e alta coesão entre os componentes.

Além disso, a implementação do Spring Data JPA em conjunto com o PostgreSQL proporciona uma abordagem robusta para a persistência de dados, permitindo consultas eficientes e um gerenciamento adequado das transações. O uso de Feign Client para integração com APIs externas torna o consumo de serviços remoto mais simplificado e seguro.

O projeto também adota boas práticas de desenvolvimento, como injeção de dependência via Spring, uso de DTOs para encapsulamento de dados, e mapeamento eficiente entre entidades e objetos de transferência via MapStruct. A implementação de Swagger/OpenAPI permite uma documentação clara e acessível da API, facilitando a integração com outras aplicações e serviços.

Dessa forma, a Up Down List API fornece uma solução escalável e bem estruturada para a manipulação de arquivos .pdf, promovendo desempenho otimizado, segurança e facilidade de manutenção. A modularidade do projeto garante flexibilidade para futuras expansões e melhorias, mantendo um ciclo de vida sustentável e eficiente.

## Considerações Finais

Se você precisa de uma solução confiável para gerenciar arquivos PDF, seja para armazenar, organizar ou integrar com outros sistemas, a Up Down List API está pronta para suprir essa demanda. Fácil de configurar, altamente personalizável e com suporte a tecnologias modernas, esse sistema se destaca como uma escolha ideal para qualquer projeto que envolva manipulação de documentos digitais.

📢 Não perca tempo! Clone o repositório, explore a API e veja como ela pode otimizar a sua gestão de arquivos!

Autor: Wesley

📧 Email: wesleymrosa@gmail.com🔗 LinkedIn: www.linkedin.com/in/wesley-martins-rosa-5118aa15a


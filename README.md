# Iniciando

Projeto simples para cadastro de clientes em um banco de dados em memória.

## Tecnologias e referencias de documentação
Este projeto utiliza as seguintes tecnologias:

* [Java 11](https://www.oracle.com/br/java/)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.2/maven-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#web)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#web.security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#howto.data-initialization.migration-tool.flyway)
* [Validation](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#io.validation)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#web.servlet.spring-mvc.template-engines)
* [Lombok](https://projectlombok.org/)
* [H2 Database](https://www.h2database.com/html/main.html)
* [Swagger with Springdoc OpenApi](https://springdoc.org/)

## Informações de Usuário e Perfil

- Usuários:
    * Login: admin, Senha: admin123
    * Login: user, Senha: user123

- Perfil:
    * ADMIN: Acesso a todos os verbos do Http (Get, Post, Delete, Update)
    * USER: Acesso restrito apenas ao verbo Get do Http

## Informações para execução dos End-Points da aplicação via SWAGGER.

- É necessário inicializar a aplicação e digitar a seguinte URL em seu browser.
    http://localhost:8090/swagger-ui.html

## Informações para execução dos End-Points de manutenção de Clientes via POSTMAN.

- Para todas as opções, usar 'Basic Auth' como "Autorization" passando um dos 2 usuários citados acima.
  * Obs.1: Atentar-se as regras de autorização de usuário/perfil descritas acima!
  * Obs.2: Os campos CPF, Nome, E-mail e Data de Nascimento possuem validação de conteúdo! 
           Para utilizar um CPF válido para testes, acesse o site: (https://www.geradorcpf.com/)

- Execução:

    * Buscar todos os clientes
        - Método HTTP: GET
        - URL: localhost:8090/rest/clients

    * Buscar determinado cliente
        - Método HTTP: GET
        - URL: localhost:8090/rest/clients/{id do cliente}

    * Salvar determinado cliente
        - Método HTTP: POST
        - URL: localhost:8090/rest/clients
        - Body: 
        ```bash
            {
                "cpf": "012.345.678-90",
                "name": "Cliente1",
                "sex": "M",
                "email": "teste@tst.com",
                "naturality": "Curitiba",
                "nacionality" : "Brasileiro",
                "dtBirthday" : "01/08/2022"
            }
        ```

    * Atualizar determinado cliente
        - Método HTTP: PUT
        - URL: localhost:8090/rest/clients/{id do cliente}
        - Body: 
        ```bash
            {
                "cpf": "012.345.678-90",
                "name": "Cliente no. 1",
                "sex": "F",
                "email": "tst@tst.com",
                "naturality": "Curitiba",
                "nacionality" : "Brasileiro",
                "dtBirthday" : "01/07/2022"
            }
        ```

    * Deletar determinado cliente
        - Método HTTP: Delete
        - URL: localhost:8090/rest/clients/{id do cliente}


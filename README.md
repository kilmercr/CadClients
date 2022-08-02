# Getting Started

** Projeto simples para cadastro de clientes em um banco de dados em memória.

Este projeto utiliza as seguintes tecnologias:
    - Java 11
    - Lombok
    - Spring Boot
    - Spring Data JPA
    - Spring Security
    - Spring Validator
    - H2 Database
    - Flyway
    - Thymeleaf (front-end)

- Usuários:
    * Login: admin, Senha: admin123
    * Login: user, Senha: user123

- Perfil:
    * ADMIN: Acesso a todos os métodos Http (Get, Post, Delete, Update)
    * USER: Acesso restrito apenas aos métodos Get do Http

- Execução dos testes via Postman:

    - Para todas as opções, usar 'Basic Auth' como "Autorization" passando um dos 2 usuários citados acima.
    (Obs.: Atentar-se a regras de autorização de usuário/perfil descritas acima!)

    * Buscar todos os clientes
        - Método HTTP: GET
        - URL: localhost:8080/clients

    * Buscar determinado cliente
        - Método HTTP: GET
        - Usar Basic Auth passando um dos 2 usuários citados acima.
        - URL: localhost:8080/clients/{id do cliente}

    * Salvar determinado cliente
        - Método HTTP: POST
        - Usar Basic Auth passando um dos 2 usuários citados acima.
        - URL: localhost:8080/clients
        - Body: 
            {
                "cpf": "012.345.678-90",
                "name": "Cliente1",
                "sex": "M",
                "email": "teste@tst.com",
                "naturality": "Curitiba",
                "nacionality" : "Brasileiro",
                "dtBirthday" : "01/08/2022"
            }

    * Atualizar determinado cliente
        - Método HTTP: PUT
        - Usar Basic Auth passando um dos 2 usuários citados acima.
        - URL: localhost:8080/clients/{id do cliente}
        - Body: 
            {
                "cpf": "012.345.678-90",
                "name": "Cliente no. 1",
                "sex": "F",
                "email": "tst@tst.com",
                "naturality": "Curitiba",
                "nacionality" : "Brasileiro",
                "dtBirthday" : "01/07/2022"
            }

    * Deletar determinado cliente
        - Método HTTP: Delete
        - Usar Basic Auth passando um dos 2 usuários citados acima.
        - URL: localhost:8080/clients/{id do cliente}


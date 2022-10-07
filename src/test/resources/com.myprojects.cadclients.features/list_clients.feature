# language: pt

@client_tests
@list_clients
Funcionalidade: Listar Clients

  Cenário: Listar clientes sem filtro
    Dado possuir massa de dados de clientes salvo
    Quando pesquisar clientes sem filtro
    Então validar que o ms retornou, para pesquisa de clientes, o estado 200
    E validar que o serviço retornou todos os clientes

  Esquema do Cenário: Listar clientes com filtro
    Dado possuir massa de dados de clientes salvo
    Quando pesquisar clientes com o filtro "<filter"
    Então validar que o ms retornou, para pesquisa de clientes, o estado 200
    E validar que o serviço retornou cliente que atendo ao filtro "<filter>"

    Exemplos:
      | filter |
      | id     |
      | cpf    |
      | name   |

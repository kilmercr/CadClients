# language: pt

@list_clients
Funcionalidade: Listar Clients

  Cenário: Listar clientes sem filtro
    Dado possuir massa de dados de clientes salvo
    Quando pesquisar clientes sem filtro
    Então validar que o ms retornou, para pesquisa de clientes, o estado 200
    E validar que o serviço retornou todos os clientes


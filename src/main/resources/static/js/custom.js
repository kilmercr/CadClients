/**
 * CadClient - Javascript
 * Geral
 */

function urlSource() {
    window.open("https://github.com/kilmercr/CadClients", "_new");
}

function criarCliente() {
    location.href = '/CadClient/cadastrarCliente';
}

function listarClientes() {
    location.href = '/CadClient/listarClientes';
}

function manterCliente(clientDto) {

    clientDto.cpf = clientDto.cpf.replace(/\D/g, '');
    if (clientDto.dtBirthday != null)
        clientDto.dtBirthday = moment(clientDto.dtBirthday).format("DD/MM/YYYY");

    clientDto.dtCreate = moment(clientDto.dtCreate).format("DD/MM/YYYY HH:mm:ss");

    if (clientDto.dtUpdate != null)
        clientDto.dtUpdate = moment(clientDto.dtUpdate).format("DD/MM/YYYY HH:mm:ss");

    $.ajax({
        type: "POST",
        data: clientDto,
        url: '/CadClient/manterCliente'
    });
}

function deletarCliente(clientId) {
    console.log('O cliente, com id [ ' + clientId + ' ], será removido!');
}

function formatarCpf(valor) {
    const cpf = valor.replace(/\D/g, '');
    $("#inputCpf").val(cpf);
}
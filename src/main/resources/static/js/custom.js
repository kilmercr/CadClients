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

function editarCliente(clientDto) {

    clientDto.cpf = clientDto.cpf.replace(/\D/g, '');
    if (clientDto.dtBirthday != null)
        clientDto.dtBirthday = moment(clientDto.dtBirthday).format("YYYY-MM-DD");

    $('#modalManterCliente').modal('show');

    $("#clientIdHidden").val(clientDto.clientId);
    $("#inputNome").val(clientDto.name);
    $("#inputCpf").val(clientDto.cpf);
    $("#selectSexo").val(clientDto.sex);
    $("#inputDtNascimento").val(clientDto.dtBirthday);
    $("#inputEmail").val(clientDto.email);
    $("#inputNaturality").val(clientDto.naturality);
    $("#inputNacionality").val(clientDto.nacionality);
}

function manterCliente() {


    let cpf = $("#inputCpf").val();
    if (cpf.length !== 11) {
        alert('O CPF inválido!');
        $("#inputCpf").focus();
        return;
    }

    let nome = $("#inputNome").val();
    if (nome === '') {
        alert('É necessário digitar um nome!');
        $("#inputNome").focus();
        return;
    }

    let email = $("#inputEmail").val();
    if (email !== '' && !validarEmail(email)) {
        alert('E-mail inválido!');
        $("#inputEmail").focus();
        return;
    }

    let clientId = $("#clientIdHidden").val();
    let sex = $("#selectSexo").val();
    let naturality = $("#inputNaturality").val();
    let nacionality = $("#inputNacionality").val();

    let dtNascimento = $("#inputDtNascimento").val();
    if (dtNascimento !== '')
        dtNascimento = moment($("#inputDtNascimento").val()).format("DD/MM/YYYY");

    let clientDto = {
        name: nome,
        cpf: cpf,
        sex: sex,
        dtBirthday: dtNascimento,
        email: email,
        naturality: naturality,
        nacionality: nacionality
    };

    let url;
    let httpType;
    if (clientId !== undefined && clientId != null && clientId != "") {
        url = '/CadClient/rest/clients/' + clientId;
        httpType = 'PUT';
    } else {
        url = '/CadClient/rest/clients';
        httpType = 'POST';
    }

    console.log(clientDto, url, httpType);
    $('#modalManterCliente').modal('hide');
    $('#modalManterCliente').find('.modal-body').html('');
}

function deletarCliente(clientId) {
    console.log('O cliente, com id [ ' + clientId + ' ], será removido!');
}

function formatarCpf(valor) {
    const cpf = valor.replace(/\D/g, '');
    $("#inputCpf").val(cpf);
}

function validarEmail(email) {
    return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email);
}
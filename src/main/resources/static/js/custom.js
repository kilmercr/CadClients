/**
 * CadClient - Javascript
 * Geral
 */

function processando() {
    $.blockUI({
        message: '<img src="/CadClient/img/busy.gif" /> &nbsp; Processando...',
        baseZ: 1080,
        css: {
            border: 'none',
            padding: '15px',
            backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity: .5,
            color: '#fff'
        }
    });
}

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

    let url = '/CadClient/rest/clients';
    let httpType = 'POST';
    if (clientId !== undefined && clientId != null && clientId != "") {
        url += '/' + clientId;
        httpType = 'PUT';
    }

    console.log(clientDto, url, httpType);

    processando();
    $.ajax({
        type: httpType,
        url: url,
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(clientDto),
        success: function (response) {

            setTimeout($.unblockUI, 400);
            console.log(response);
            listarClientes();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            setTimeout($.unblockUI, 100);
            console.log(jqXHR.status, textStatus, errorThrown);
        }
    });

    $('#modalManterCliente').modal('hide');
    $('#modalManterCliente').find('.modal-body').html('');
}

function deletarCliente(clientId) {

    console.log('O cliente, com id [ ' + clientId + ' ], será removido!');
    processando();
    $.ajax({
        type: 'DELETE',
        url: '/CadClient/rest/clients/' + clientId,
        contentType: 'application/json',
        success: function (response) {

            setTimeout($.unblockUI, 100);
            console.log(response);
            listarClientes();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            setTimeout($.unblockUI, 100);
            console.log(jqXHR.status, textStatus, errorThrown);
        }
    });
}

function limparCpf(valor) {
    const cpf = valor.replace(/\D/g, '');
    $("#inputCpf").val(cpf);
}

function formatCnpjCpf(value) {
    const cnpjCpf = value.replace(/\D/g, '');

    if (cnpjCpf.length === 11) {
        return cnpjCpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "\$1.\$2.\$3-\$4");
    }

    return cnpjCpf.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/g, "\$1.\$2.\$3/\$4-\$5");
}

function validarEmail(email) {
    return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email);
}
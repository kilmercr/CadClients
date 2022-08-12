package com.myprojects.cadclients.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myprojects.cadclients.enums.SexEnum;
import com.myprojects.cadclients.model.ClientModel;
import com.myprojects.cadclients.repository.ClientRepository;
import com.myprojects.cadclients.utils.StringUtils;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService clientService;

    @Test
    void deveEncontrarTodosClientes() {
        clientService.findAll();
        verify(clientRepository).findAll();
    }

    @Test
    void deveEncontrarClientePorID() {
        clientService.findById(1l);
        verify(clientRepository).findById(1l);
    }

    @Test
    void deveEncontrarClientePorCPF() {
        String cpf = "33007748534";
        clientService.findByCpf(cpf);
        verify(clientRepository).findByCpf(cpf);
    }

    @Test
    void deveEncontrarClientePorNome() {
        String nome = "Client no. 1";
        clientService.findByName(nome);
        verify(clientRepository).findByName(nome);
    }

    @Test
    void deveEncontrarClientePorParteDoNome() {
        String nome = "Client";
        clientService.findByNameContaining(nome);
        verify(clientRepository).findByNameContaining(nome);
    }

    @Test
    void deveManterCliente() {

        ClientModel clientModel = ClientModel.builder().clientId(3L).cpf("26250236350")
                .cpfFormatado(StringUtils.formatCPF("26250236350")).name("Client no. 3").sex(SexEnum.F)
                .email("client3@tst.com.br").naturality("Curitiba").nacionality("Brasil")
                .dtBirthday(LocalDate.now(ZoneId.systemDefault()).minusYears(20L))
                .dtCreate(LocalDateTime.now(ZoneId.systemDefault())).build();
        clientService.save(clientModel);

        ArgumentCaptor<ClientModel> clientModelArgumentCaptor = ArgumentCaptor.forClass(ClientModel.class);
        verify(clientRepository).save(clientModelArgumentCaptor.capture());

        ClientModel captureClientModel = clientModelArgumentCaptor.getValue();
        assertThat(captureClientModel).isEqualTo(clientModel);
    }

    @Test
    void deveRemoverCliente() {

        ClientModel clientModel = ClientModel.builder().clientId(3L).cpf("26250236350")
                .cpfFormatado(StringUtils.formatCPF("26250236350")).name("Client no. 3").sex(SexEnum.F)
                .email("client3@tst.com.br").naturality("Curitiba").nacionality("Brasil")
                .dtBirthday(LocalDate.now(ZoneId.systemDefault()).minusYears(20L))
                .dtCreate(LocalDateTime.now(ZoneId.systemDefault())).build();

        clientService.delete(clientModel);

        ArgumentCaptor<ClientModel> clientModelArgumentCaptor = ArgumentCaptor.forClass(ClientModel.class);
        verify(clientRepository).delete(clientModelArgumentCaptor.capture());
    }
}
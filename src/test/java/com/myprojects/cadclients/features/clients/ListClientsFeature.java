package com.myprojects.cadclients.features.clients;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.springframework.security.test.context.support.WithUserDetails;

import com.myprojects.cadclients.features.FeatureBase;
import com.myprojects.cadclients.features.ScenarioFactory;
import com.myprojects.cadclients.model.ClientModel;
import com.myprojects.cadclients.repository.ClientRepository;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ListClientsFeature extends FeatureBase<ClientRepository> {

    private ClientModel clientModel;

    @WithUserDetails(value = "admin")
    @After("@list_clients")
    public void hookEachToAfter() {
        repository.deleteByNameContainingIgnoreCase(ScenarioFactory.CLIENT_DEFAULT_NAME);
    }

    @WithUserDetails(value = "admin")
    @Given("possuir massa de dados de clientes salvo")
    public void createClientsByDto() {
        requestCreate("rest/clients", ScenarioFactory.createClientDto(), ClientModel.class);
        var response = requestCreate("rest/clients", ScenarioFactory.createClientDto(), ClientModel.class);
        clientModel = response.getBody();
    }

    @WithUserDetails(value = "admin")
    @When("pesquisar clientes sem filtro")
    public void searchClientWithoutFilter() {
        expectedResponseEntity = requestFind("rest/clients", Object.class);
    }

    @Then("validar que o ms retornou, para pesquisa de clientes, o estado {int}")
    public void validateMSReturnedStatusSpecificForClient(int httpStatus) {
        assertEquals(httpStatus, expectedResponseEntity.getStatusCode().value());
    }

    @And("validar que o serviço retornou todos os clientes")
    public void validateServiceReturnedAllClients() {
        var listOfClients = (ArrayList) mapper.map(expectedResponseEntity.getBody(), Object.class);
        assertFalse(listOfClients.isEmpty());
        assertTrue(listOfClients.size() >= 2);
    }

    @WithUserDetails(value = "admin")
    @When("pesquisar clientes com o filtro {string}")
    public void searchClientWithFilter(String filter) {
        if (filter.equalsIgnoreCase("id")) {
            expectedResponseEntity = requestFindById("rest/clients", clientModel.getClientId(), Object.class);
        } else if (filter.equalsIgnoreCase("cpf")) {
            expectedResponseEntity = requestFindById("rest/clients/cpf", clientModel.getCpf(), Object.class);
        } else if (filter.equalsIgnoreCase("name")) {
            expectedResponseEntity = requestFindById("rest/clients/name", clientModel.getName(), Object.class);
        }
    }

    @And("validar que o serviço retornou cliente que atendo ao filtro {string}")
    public void validateServiceReturnedSpecificClientSearch(String filter) {
        var client = (ClientModel) mapper.map(expectedResponseEntity.getBody(), Object.class);
        if (filter.equalsIgnoreCase("id")) {
            assertEquals(clientModel.getClientId(), client.getClientId());
        } else if (filter.equalsIgnoreCase("cpf")) {
            assertEquals(clientModel.getCpf(), client.getCpf());
        } else if (filter.equalsIgnoreCase("name")) {
            assertEquals(clientModel.getName(), client.getName());
        }
    }
}

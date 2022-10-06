package com.myprojects.cadclients.features.clients;

import com.myprojects.cadclients.features.FeatureBase;
import com.myprojects.cadclients.features.ScenarioFactory;
import com.myprojects.cadclients.model.ClientModel;
import com.myprojects.cadclients.repository.ClientRepository;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class ListClientsFeature extends FeatureBase<ClientRepository> {


    private ClientModel clientModel;

    @After("@list_clients")
    public void hookEachToAfter() {
        repository.deleteByNameContainingIgnoreCase(ScenarioFactory.CLIENT_DEFAULT_NAME);
    }

    @Given("possuir massa de dados de clientes salvo")
    public void createClientsByDto() {
        requestCreate("rest/clients", ScenarioFactory.createClientDto(), ClientModel.class);
        var response = requestCreate("rest/clients", ScenarioFactory.createClientDto(), ClientModel.class);
        clientModel = response.getBody();
    }

    @When("pesquisar clientes sem filtro")
    public void searchClientWithoutFilter() {
        expectedResponseEntity = requestFind("rest/clients", Object.class);
    }
}

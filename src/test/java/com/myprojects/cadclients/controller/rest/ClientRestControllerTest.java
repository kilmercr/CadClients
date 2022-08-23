package com.myprojects.cadclients.controller.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.myprojects.cadclients.enums.SexEnum;
import com.myprojects.cadclients.model.ClientModel;
import com.myprojects.cadclients.service.ClientService;
import com.myprojects.cadclients.utils.StringUtils;

@AutoConfigureMockMvc
@SpringBootTest
class ClientRestControllerTest {

    @MockBean
    private ClientService clientService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void getAllClients() throws Exception {
        Mockito.when(clientService.findAll()).thenReturn(List.of(
                ClientModel.builder().clientId(1L).cpf("33007748534").cpfFormatado(StringUtils.formatCPF("33007748534"))
                        .name("Client no. 1").sex(SexEnum.F).email("client1@tst.com.br").naturality("Curitiba")
                        .nacionality("Brasil").dtBirthday(LocalDate.now(ZoneId.systemDefault()).minusYears(20L))
                        .dtCreate(LocalDateTime.now(ZoneId.systemDefault())).build(),
                ClientModel.builder().clientId(2L).cpf("91606820516").cpfFormatado(StringUtils.formatCPF("91606820516"))
                        .name("Client no. 2").sex(SexEnum.M).email("client2@tst.com.br").naturality("Joao Pessoa")
                        .nacionality("Brasil").dtBirthday(LocalDate.now(ZoneId.systemDefault()).minusYears(25L))
                        .dtCreate(LocalDateTime.now(ZoneId.systemDefault())).build()));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/rest/clients")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("client2@tst.com.br"));
    }

    @Test
    @WithMockUser
    void getClientById() throws Exception {

        Optional<ClientModel> optCM = Optional.of(
                ClientModel.builder().clientId(1L).cpf("33007748534").cpfFormatado(StringUtils.formatCPF("33007748534"))
                        .name("Client no. 1").sex(SexEnum.F).email("client1@tst.com.br").naturality("Curitiba")
                        .nacionality("Brasil").dtBirthday(LocalDate.now(ZoneId.systemDefault()).minusYears(20L))
                        .dtCreate(LocalDateTime.now(ZoneId.systemDefault())).build());

        Mockito.when(clientService.findById(1L)).thenReturn(optCM);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/rest/clients/1")
                .contentType(MediaType.APPLICATION_JSON));

        String json = objectMapper.writeValueAsString(resultActions.andReturn().getResponse().getContentAsString());
        if (!json.isBlank()) {
            json = json.replace("\\", "");
            json = json.substring(1, json.length()-1);
        }

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class,
                (JsonDeserializer<LocalDate>) (jsonLD, type, jsonDeserializationContext)
                        -> LocalDate.parse(jsonLD.getAsString()));
        builder.registerTypeAdapter(LocalDateTime.class,
                (JsonDeserializer<LocalDateTime>) (jsonLDT, type, jsonDeserializationContext)
                        -> LocalDateTime.parse(jsonLDT.getAsString()));

        Gson gson = builder.create();
        Type clientModelType = new TypeToken<ClientModel>() {}.getType();
        ClientModel clientModel = gson.fromJson(json,clientModelType);
        assertEquals(1L, clientModel.getClientId());
    }

    @Test
    @WithUserDetails(value = "admin")
    void saveClient() throws Exception {

        String jsonClientModel = "{\"cpf\":\"26250236350\",\"name\":\"Client no. 3\",\"sex\":\"F\"," +
                "\"email\":\"client3@tst.com.br\",\"naturality\":\"Brasilia\",\"nacionality\":\"Brasil\"," +
                "\"dtBirthday\":\"13/08/2007\"}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/rest/clients")
                .contentType(MediaType.APPLICATION_JSON).content(jsonClientModel)
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
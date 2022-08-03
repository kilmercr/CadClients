package com.myprojects.cadclients.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myprojects.cadclients.dtos.ClientDto;
import com.myprojects.cadclients.enums.SexEnum;
import com.myprojects.cadclients.model.ClientModel;
import com.myprojects.cadclients.service.ClientService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class HomeController {

    private final ClientService clientService;

    @GetMapping(value = { "/login", "/index", "/" })
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/listarClientes")
    public ModelAndView listarClientes() {
        ModelAndView model = new ModelAndView("listarClientes");

        Collection<ClientModel> lstClient = clientService.findAll();
        lstClient.add(ClientModel.builder().clientId(1L).cpf("01234567890").name("Client no. 1").sex(SexEnum.F)
                .email("client1@tst.com.br").naturality("Curitiba").nacionality("Brasil")
                .dtBirthday(LocalDate.now(ZoneId.systemDefault()).minusYears(30L))
                .dtCreate(LocalDateTime.now(ZoneId.systemDefault())).build());
        lstClient.add(ClientModel.builder().clientId(2L).cpf("98765432109").name("Client no. 2").sex(SexEnum.M)
                .email("client2@tst.com.br").naturality("Curitiba").nacionality("Brasil")
                .dtBirthday(LocalDate.now(ZoneId.systemDefault()).minusYears(20L))
                .dtCreate(LocalDateTime.now(ZoneId.systemDefault()).minusDays(20L))
                .dtUpdate(LocalDateTime.now(ZoneId.systemDefault())).build());

        model.addObject("lstClient", lstClient);
        return model;
    }

    @GetMapping("/cadastrarCliente")
    public ModelAndView cadastrarCliente() {
        return new ModelAndView("cadastrarCliente");
    }

    @PostMapping("/editarCliente")
    public ModelAndView editarCliente(@RequestBody @Valid ClientDto clientDto) {
        ModelAndView model = new ModelAndView("cadastrarCliente");
        model.addObject("name", clientDto.getName());
        return model;
    }

}

package com.myprojects.cadclients.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myprojects.cadclients.enums.SexEnum;
import com.myprojects.cadclients.model.ClientModel;
import com.myprojects.cadclients.service.ClientService;
import com.myprojects.cadclients.utils.StringUtils;

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
        lstClient.add(ClientModel.builder().clientId(1L).cpf("01234567890")
                .cpfFormatado(StringUtils.formatCPF("01234567890")).name("Client no. 1").sex(SexEnum.F)
                .email("client1@tst.com.br").naturality("Curitiba").nacionality("Brasil")
                .dtBirthday(LocalDate.now(ZoneId.systemDefault()).minusYears(30L))
                .dtCreate(LocalDateTime.now(ZoneId.systemDefault())).build());
        lstClient.add(ClientModel.builder().clientId(2L).cpf("98765432109")
                .cpfFormatado(StringUtils.formatCPF("98765432109")).name("Client no. 2").sex(SexEnum.M)
                .email("client2@tst.com.br").naturality("Curitiba").nacionality("Brasil")
                .dtBirthday(LocalDate.now(ZoneId.systemDefault()).minusYears(20L))
                .dtCreate(LocalDateTime.now(ZoneId.systemDefault()).minusDays(20L))
                .dtUpdate(LocalDateTime.now(ZoneId.systemDefault())).build());

        model.addObject("lstClient", lstClient);
        return model;
    }

    @GetMapping("/exibirModalCliente")
    public ModelAndView exibirModalCliente() {
        return new ModelAndView("modalContentManterCliente");
    }

}

package com.myprojects.cadclients.controller;

import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myprojects.cadclients.model.ClientModel;
import com.myprojects.cadclients.service.ClientService;
import com.myprojects.cadclients.utils.StringUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class HomeController {

    private final ClientService clientService;

    @GetMapping({ "/index", "/" })
    public ModelAndView index() {

        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView login() {

        return new ModelAndView("login");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/listarClientes")
    public ModelAndView listarClientes() {

        ModelAndView model = new ModelAndView("listarClientes");

        Collection<ClientModel> lstClient = clientService.findAll();
        if (!lstClient.isEmpty())
            lstClient.stream().forEach(c -> c.setCpfFormatado(StringUtils.formatCPF(c.getCpf())));

        model.addObject("lstClient", lstClient);
        return model;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/exibirModalCliente")
    public ModelAndView exibirModalCliente() {

        return new ModelAndView("modalContentManterCliente");
    }

}

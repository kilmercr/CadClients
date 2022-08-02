package com.myprojects.cadclients.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myprojects.cadclients.service.ClientService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class HomeController {

    private final ClientService clientService;

    @GetMapping
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("index");
        return model;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView("login");
        return model;
    }

}

package com.example.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeAction {
    @RequestMapping("/")
    public String homeAction(){
        return "index";
    }
}

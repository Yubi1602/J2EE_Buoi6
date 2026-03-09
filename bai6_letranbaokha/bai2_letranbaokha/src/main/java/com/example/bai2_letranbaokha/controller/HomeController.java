package com.example.bai2_letranbaokha.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class HomeController {
    @GetMapping("/home")
  public String home(Principal principal){
        return "hello" + principal.getName();
    }






}


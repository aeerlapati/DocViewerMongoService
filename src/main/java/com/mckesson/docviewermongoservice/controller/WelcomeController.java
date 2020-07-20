package com.mckesson.docviewermongoservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    
    @GetMapping("/canary")
    public String checkCanary() {
        return "Tweet";
    }
}
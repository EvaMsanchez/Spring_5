package com.eva.curso.springboot.error.springboot_error.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController 
{
    @GetMapping("/app")
    public String index()
    {
        // No se puede dividir por 0 porque es infinito (error 500: ArithmeticException)
        int value = 100/0;
        System.out.println(value);
        return "ok 200";
    }    
}

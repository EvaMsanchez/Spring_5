package com.eva.curso.springboot.error.springboot_error.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController 
{
    @GetMapping("/app")
    public String index()
    {
        //int value = 100/0; // No se puede dividir por 0 porque es infinito (error 500: ArithmeticException)

        int value = Integer.parseInt("10x"); // No se puede formatear a número porque contiene letra
        System.out.println(value);
        return "ok 200";
    }    
}

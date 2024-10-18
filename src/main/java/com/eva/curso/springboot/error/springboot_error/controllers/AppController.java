package com.eva.curso.springboot.error.springboot_error.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.curso.springboot.error.springboot_error.exceptions.UserNotFounException;
import com.eva.curso.springboot.error.springboot_error.models.domain.User;
import com.eva.curso.springboot.error.springboot_error.services.UserService;

@RestController
@RequestMapping("/app")
public class AppController 
{
    @Autowired
    private UserService service;


    @GetMapping
    public String index()
    {
        //int value = 100/0; // No se puede dividir por 0 porque es infinito (error 500: ArithmeticException)

        int value = Integer.parseInt("10x"); // No se puede formatear a número porque contiene letra
        System.out.println(value);
        return "ok 200";
    }    


    @GetMapping("/show/{id}")
    public User show(@PathVariable Long id)
    {
        /* Forzamos para que salga un error (excepción NullPointerException) creando un objeto(id) que no existe,
        si ponemos un id que existe al tener el role vacío sin definir pues no se puede obtener el nombre del role 
        y lanza otro error (excepción HttpMessageNotWritableException) */
        // Si el usuario no existe devuelve la excepción personalizada y si existe devuelve el usuario
        User user = service.findById(id).orElseThrow(() -> new UserNotFounException("Error el usuario no existe")); // Para evitar el NullPointException); 
        System.out.println(user.getLastname());
        return user;
    }
}

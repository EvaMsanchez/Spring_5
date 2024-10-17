package com.eva.curso.springboot.error.springboot_error.services;

import java.util.List;

import com.eva.curso.springboot.error.springboot_error.models.domain.User;

public interface UserService 
{
    // Métodos contratos que tiene que implementar una clase (public al ser interfaz no se necesita)
    List<User> findAll();
    User findById(Long id);
}

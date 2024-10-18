package com.eva.curso.springboot.error.springboot_error.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eva.curso.springboot.error.springboot_error.models.domain.User;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private List<User> users;


    @Override
    public List<User> findAll() 
    {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) 
    {
        
        /*for (User u : users) 
        {
            if(u.getId().equals(id)) // equals: compara valores, el contenido.
            {
                user = u;
                break;
            } 
        }
        return Optional.ofNullable(user); // Si es null el usuario devuelve vacío
        */

        // Otra forma en vez de con el for, utilizando Api stream
        return users.stream().filter(usr -> usr.getId().equals(id)).findFirst();
    }

}

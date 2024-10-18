package com.eva.curso.springboot.error.springboot_error.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eva.curso.springboot.error.springboot_error.models.domain.User;

@Service
public class UserServiceImpl implements UserService
{
    private List<User> users;

    public UserServiceImpl()
    {
        // ArraysList: para que luego pueda ser modificada la lista, si utilizamos Arrays.asList es fija no podrá ser modificada
        this.users = new ArrayList<>();

        // Emular una base de datos, pero deben llegar de una consulta
        users.add(new User(1L, "Pepe", "González"));
        users.add(new User(2L, "Andrés", "Mena"));
        users.add(new User(3L, "María", "Pérez"));
        users.add(new User(4L, "Josefa", "Ramírez"));
        users.add(new User(5L, "Ale", "Gutierrez"));
    }

    @Override
    public List<User> findAll() 
    {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) 
    {
        User user = null;
        for (User u : users) 
        {
            if(u.getId().equals(id)) // equals: compara valores, el contenido.
            {
                user = u;
                break;
            } 
        }
        return Optional.ofNullable(user); // Si es null el usuario devuelve vacío
    }

}

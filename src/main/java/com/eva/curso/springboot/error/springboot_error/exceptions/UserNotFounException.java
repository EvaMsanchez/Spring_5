package com.eva.curso.springboot.error.springboot_error.exceptions;

public class UserNotFounException extends RuntimeException
{
    public UserNotFounException(String message)
    {
        super(message);
    }
}

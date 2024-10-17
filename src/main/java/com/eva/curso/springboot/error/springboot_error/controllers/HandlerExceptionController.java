package com.eva.curso.springboot.error.springboot_error.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.eva.curso.springboot.error.springboot_error.models.Error;

@RestControllerAdvice
public class HandlerExceptionController 
{
    @ExceptionHandler({ArithmeticException.class})
    // Devuelve un objeto que va en el cuerpo de la respuesta
    public ResponseEntity<Error> divisionByZero(Exception ex)
    {
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Error división por cero!");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        
        return ResponseEntity.internalServerError().body(error);
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> notFoundEx(NoHandlerFoundException e)
    {
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Api rest no encontrado");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        
        return ResponseEntity.status(404).body(error);
        // return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);

        // Hay que añadir una configuración en el application.properties para que se pueda mostrar el mensaje personalizado en el error 404
    }
}

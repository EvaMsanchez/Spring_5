package com.eva.curso.springboot.error.springboot_error.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.eva.curso.springboot.error.springboot_error.exceptions.UserNotFounException;
import com.eva.curso.springboot.error.springboot_error.models.Error;

@RestControllerAdvice
public class HandlerExceptionController 
{
    // Error 500 (no se puede dividir entre 0)
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


    // Error 500 (no se puede formatear a número porque contiene letra)
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> numberFormatException(Exception ex)
    {
        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "Número inválido o incorrecto, no tiene formato de dígito");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        
        return error;
        // return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }


    // Error 500 (nullPointer: el usuario no existe, httpMessageNotWritable: el role es null)
    @ExceptionHandler({NullPointerException.class,
        HttpMessageNotWritableException.class,
        UserNotFounException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> userNotFoundException(Exception ex)
    {
        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "El usuario o role no existe!");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        
        return error;
        // return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }


    // Error 404 (no encuentra la ruta)
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

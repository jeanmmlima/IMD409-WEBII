package com.jeanlima.springrestapiapp.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.jeanlima.springrestapiapp.exception.RegraNegocioException;
import com.jeanlima.springrestapiapp.rest.ApiErrors;



@RestControllerAdvice //trata os exceptionHandlers --> trata erros quando elesa acontecem
public class ApplicationControllerAdvice {

    /* 
     * toda vez que api lançar essa exceção, cairá aqui!
     * preciso dizer qual status será retornado - por padrão -bad request - 400
    */
    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }
}

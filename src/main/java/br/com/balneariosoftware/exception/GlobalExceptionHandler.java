package br.com.balneariosoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Excessões globais
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGlobalException(Exception exception, WebRequest request) {
        DetalhesErros detalhesErros = new DetalhesErros(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(detalhesErros, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        DetalhesErros detalhesErros = new DetalhesErros(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(detalhesErros, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity handleAPIException(APIException exception, WebRequest request) {
        DetalhesErros detalhesErros = new DetalhesErros(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(detalhesErros, HttpStatus.NOT_FOUND);
    }
}

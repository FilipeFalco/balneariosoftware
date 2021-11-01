package br.com.balneariosoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IsFuncionario extends RuntimeException{

    public IsFuncionario(String message) {
        super(message);
    }
}

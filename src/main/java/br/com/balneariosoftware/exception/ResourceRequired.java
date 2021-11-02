package br.com.balneariosoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceRequired extends RuntimeException{

    public ResourceRequired(String message) {
        super(message);
    }
}

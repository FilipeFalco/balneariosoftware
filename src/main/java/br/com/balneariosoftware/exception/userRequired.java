package br.com.balneariosoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class userRequired extends RuntimeException{

    public userRequired(String message) {
        super(message);
    }
}

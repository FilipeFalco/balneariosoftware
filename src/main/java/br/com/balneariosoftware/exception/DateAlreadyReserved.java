package br.com.balneariosoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DateAlreadyReserved extends RuntimeException{

    public DateAlreadyReserved (String message) {
        super(message);
    }
}

package br.com.balneariosoftware.exception;

import java.util.Date;

public class DetalhesErros {

    private Date timestamp;
    private String message;
    private String details;

    public DetalhesErros(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}

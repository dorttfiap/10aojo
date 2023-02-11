package br.com.fiap.abctechapi.handler.exception;

import lombok.Getter;

@Getter
public class CoordinatesException extends RuntimeException {

    private String description;

    public CoordinatesException(String message, String description) {
        super(message);
        this.description = description;
    }
}

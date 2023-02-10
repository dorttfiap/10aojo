package br.com.fiap.abctechapi.handler.exception;

import lombok.Getter;

@Getter
public class MinimumAssistsException extends RuntimeException {

    private String description;

    public MinimumAssistsException(String message, String description) {
        super(message);
        this.description = description;
    }
}

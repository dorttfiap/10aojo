package br.com.fiap.abctechapi.handler;

import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MinimumAssistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MinimumAssistsException.class)
    public ResponseEntity<ErrorMessageResponse> errorMinAssistRequired(MinimumAssistsException exception) {
        ErrorMessageResponse error = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(), exception.getMessage(),
                exception.getDescription()
        );

        return new ResponseEntity<ErrorMessageResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxAssistsException.class)
    public ResponseEntity<ErrorMessageResponse> errorMinAssistRequired(MaxAssistsException exception) {
        ErrorMessageResponse error = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getMessage(),
                exception.getDescription()
        );

        return new ResponseEntity<ErrorMessageResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageResponse> validationException(MethodArgumentNotValidException exception) {

        ErrorMessageResponse error = new ErrorMessageResponse (
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                "Validation Error",
                exception.getAllErrors().get(0).getDefaultMessage()
        );

        return new ResponseEntity<ErrorMessageResponse>(error, HttpStatus.BAD_REQUEST);
    }
}

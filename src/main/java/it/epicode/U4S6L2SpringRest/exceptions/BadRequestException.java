package it.epicode.U4S6L2SpringRest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public List<ObjectError> exceptionsList;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<ObjectError> exceptionsList) {
        super("Errors retrieved in the body.");
        this.exceptionsList = exceptionsList;
    }

    public List<ObjectError> getExceptionsList() {
        return exceptionsList;
    }
}

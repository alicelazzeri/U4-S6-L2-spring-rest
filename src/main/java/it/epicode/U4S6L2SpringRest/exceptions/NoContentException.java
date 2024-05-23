package it.epicode.U4S6L2SpringRest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {
    public NoContentException(String message) {
        super(message);
    }
}

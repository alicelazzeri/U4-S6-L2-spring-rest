package it.epicode.U4S6L2SpringRest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException (String message) {
        super(message);
    }
    public NotFoundException (long id) {
        super("Item with " + id + " not found.");
    }
}

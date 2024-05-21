package it.epicode.U4S6L2SpringRest.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException (long id) {
        super("Item with " + id + " not found.");
    }
}

package it.epicode.U4S6L2SpringRest.exceptions;

import org.hibernate.annotations.NotFound;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice

public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<?> handleGenericException(Exception e) {
        ResponseEntity<?> responseEntity = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        ResponseEntity<?> responseEntity = new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        ResponseEntity<?> responseEntity = new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }


}

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

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice

public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<?> handleGenericException(Exception e) {
        ExceptionPayload payload = new ExceptionPayload(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        ResponseEntity<?> responseEntity = new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        ExceptionPayload payload = new ExceptionPayload(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        ResponseEntity<?> responseEntity = new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        ExceptionPayload payload = new ExceptionPayload(e.getMessage(), HttpStatus.BAD_REQUEST,  LocalDateTime.now());
        ResponseEntity<?> responseEntity = new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    protected ResponseEntity<?> handleNoContentException(NoContentException e) {
        ExceptionPayload payload = new ExceptionPayload(e.getMessage(), HttpStatus.NO_CONTENT,LocalDateTime.now());
        ResponseEntity<?> responseEntity = new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        return responseEntity;
    }
}

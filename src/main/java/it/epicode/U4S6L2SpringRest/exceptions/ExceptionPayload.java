package it.epicode.U4S6L2SpringRest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor

public class ExceptionPayload {
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime createdAt;
}

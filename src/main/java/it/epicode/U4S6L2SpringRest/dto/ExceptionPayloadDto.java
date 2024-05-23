package it.epicode.U4S6L2SpringRest.dto;
import it.epicode.U4S6L2SpringRest.exceptions.ExceptionPayload;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public record ExceptionPayloadDto(
        String message,
        HttpStatus httpStatus,
        LocalDateTime createdAt
) {
    public ExceptionPayloadDto(String message, HttpStatus httpStatus, LocalDateTime createdAt) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.createdAt = createdAt;
    }
}

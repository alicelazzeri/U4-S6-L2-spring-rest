package it.epicode.U4S6L2SpringRest.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ExceptionPayloadWithListDto (
        String message,
        HttpStatus httpStatus,
        LocalDateTime createdAt,
        List<org.springframework.validation.ObjectError> exceptionsList
) {}

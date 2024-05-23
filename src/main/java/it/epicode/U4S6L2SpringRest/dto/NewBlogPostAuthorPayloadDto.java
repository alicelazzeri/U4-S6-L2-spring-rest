package it.epicode.U4S6L2SpringRest.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewBlogPostAuthorPayloadDto(
        @NotNull (message = "The author name is compulsory.")
        String firstName,
        @NotNull (message = "The author surname is compulsory.")
        String lastName,
        @NotNull (message = "The author's email is compulsory.")
        String email,
        @NotNull (message = "The author's birthday is compulsory.")
        LocalDate dateOfBirth,
        String avatar
) {}

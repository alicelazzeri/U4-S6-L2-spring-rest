package it.epicode.U4S6L2SpringRest.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewBlogPostPayloadDto(
     @NotNull (message = "Title is mandatory.")
     @NotEmpty(message = "Title cannot be empty.")
     String title,
     String cover,
     String content,
     @NotNull (message = "Reading time is mandatory.")
     int readingTime,
     @NotNull (message = "Category is mandatory.")
     String category,
     @NotNull(message = "Author ID is mandatory.")
     long authorId
) {}

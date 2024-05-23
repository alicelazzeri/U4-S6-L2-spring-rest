package it.epicode.U4S6L2SpringRest.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")

public class NewBlogPostPayload {

    private String category;
    private String title;
    private String cover;
    private String content;
    private int readingTime;
    private long authorId;
}

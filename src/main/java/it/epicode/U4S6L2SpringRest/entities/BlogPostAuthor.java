package it.epicode.U4S6L2SpringRest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "blog_post_authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder (setterPrefix = "with")

public class BlogPostAuthor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;
    @Column
    private String avatar;
}

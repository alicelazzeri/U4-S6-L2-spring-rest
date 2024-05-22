package it.epicode.U4S6L2SpringRest.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "blog_posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class NewBlogPostPayload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String category;
    @Column
    private String title;
    @Column
    private String cover;
    @Column
    private String content;
    @Column
    private int readingTime;
    @Column
    private long blogPostAuthor;
}

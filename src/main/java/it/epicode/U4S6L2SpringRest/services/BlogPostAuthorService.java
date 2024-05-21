package it.epicode.U4S6L2SpringRest.services;

import it.epicode.U4S6L2SpringRest.entities.BlogPostAuthor;
import it.epicode.U4S6L2SpringRest.exceptions.NotFoundException;
import it.epicode.U4S6L2SpringRest.repositories.BlogPostAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlogPostAuthorService {

    @Autowired
    private BlogPostAuthorRepository blogPostAuthorRepository;

    public BlogPostAuthor saveBlogPostAuthor(BlogPostAuthor blogPostAuthor) {
        BlogPostAuthor.builder()
                .withFirstName("Alice")
                .withLastName("Lazzeri")
                .withEmail("alice@gmail.com")
                .withDateOfBirth(LocalDate.of(1995, 9, 2))
                .withAvatar("https://ui-avatars.com/api/?name=" + blogPostAuthor.getFirstName() + "+" + blogPostAuthor.getLastName())
                        .build();
        blogPostAuthorRepository.save(blogPostAuthor);
        return blogPostAuthor;
    }

    public List<BlogPostAuthor> findAllBlogPostAuthors() {
        return blogPostAuthorRepository.findAll();
    }

    public BlogPostAuthor findBlogPostAuthorById(long id) {
        return blogPostAuthorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void deleteBlogPostAuthor(long id) {
        blogPostAuthorRepository.deleteById(id);
    }
}

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

    // GET all

    public List<BlogPostAuthor> getAllBlogPostAuthors() {
        return blogPostAuthorRepository.findAll();
    }

    // GET

    public BlogPostAuthor getBlogPostAuthorById(long id) {
        return blogPostAuthorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // POST

    public BlogPostAuthor saveBlogPostAuthor(BlogPostAuthor blogPostAuthor) {
        if (blogPostAuthor.getAvatar().isEmpty() || blogPostAuthor.getAvatar() == null) {
            blogPostAuthor.setAvatar("https://ui-avatars.com/api/?name=" + blogPostAuthor.getFirstName() + "+" + blogPostAuthor.getLastName());
        }
        blogPostAuthorRepository.save(blogPostAuthor);
        return blogPostAuthor;
    }

    // PUT

    public BlogPostAuthor updateBlogPostAuthor(long id, BlogPostAuthor updatedBlogPostAuthor) {
        var blogPostAuthor = blogPostAuthorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        blogPostAuthor.setFirstName("Nicola");
        blogPostAuthor.setLastName("Rossi");
        blogPostAuthor.setEmail("nicoross@live.com");
        blogPostAuthor.setDateOfBirth(LocalDate.of(1196, 3, 30));
        blogPostAuthor.setAvatar("https://ui-avatars.com/api/?name=" + updatedBlogPostAuthor.getFirstName() + "+" + updatedBlogPostAuthor.getLastName());
        blogPostAuthorRepository.save(blogPostAuthor);
        return blogPostAuthor;
    }

    // DELETE

    public void deleteBlogPostAuthor(long id) {
        blogPostAuthorRepository.deleteById(id);
    }
}

package it.epicode.U4S6L2SpringRest.services;

import it.epicode.U4S6L2SpringRest.entities.BlogPost;
import it.epicode.U4S6L2SpringRest.exceptions.NotFoundException;
import it.epicode.U4S6L2SpringRest.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    // GET all

    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    // GET

    public BlogPost getBlogPostById(long id) {
        return blogPostRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    // POST

    public BlogPost saveBlogPost(BlogPost blogPost) {
        if (blogPost.getCover().isEmpty() || blogPost.getCover() == null) {
            blogPost.setCover("https://picsum.photos/200/300");
        }
        blogPostRepository.save(blogPost);
        return blogPost;
    }

    // PUT

    public BlogPost updateBlogPost(long id, BlogPost updatedBlogPost) {
        var blogPost = blogPostRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        blogPost.setCategory("History");
        blogPost.setTitle("WWI (1914-1918");
        blogPost.setCover("https://picsum.photos/id/11/200/300");
        blogPost.setContent("World War I or the First World War (28 July 1914 – 11 November 1918) " +
                "was a global conflict between two coalitions: " +
                "the Allies and the Central Powers.");
        blogPost.setReadingTime(Duration.ofMinutes(5));
        return blogPost;
    }

    // DELETE

    public void deleteBlogPost(long id) {
        blogPostRepository.deleteById(id);
    }




}

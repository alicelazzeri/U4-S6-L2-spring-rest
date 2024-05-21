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
        if (blogPost.getCover() == null || blogPost.getCover().isEmpty()) {
            blogPost.setCover("https://picsum.photos/200/300");
        }
        blogPostRepository.save(blogPost);
        return blogPost;
    }

    // PUT

    public BlogPost updateBlogPost(long id, BlogPost updatedBlogPost) {
        var blogPost = blogPostRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        blogPost.setCategory(updatedBlogPost.getCategory());
        blogPost.setTitle(updatedBlogPost.getTitle());
        blogPost.setCover(updatedBlogPost.getCover());
        blogPost.setContent(updatedBlogPost.getContent());
        blogPost.setReadingTime(updatedBlogPost.getReadingTime());
        blogPostRepository.save(blogPost);
        return blogPost;
    }

    // DELETE

    public void deleteBlogPost(long id) {
        blogPostRepository.deleteById(id);
    }
}

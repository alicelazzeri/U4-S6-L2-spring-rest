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

    public BlogPost saveBlogPost(BlogPost blogPost) {
        BlogPost.builder()
                .withCategory("History")
                .withTitle("WWI (1914-1918")
                .withCover("https://picsum.photos/200/300")
                .withContent("World War I or the First World War (28 July 1914 – 11 November 1918) " +
                        "was a global conflict between two coalitions: " +
                        "the Allies and the Central Powers.")
                .withReadingTime(Duration.ofMinutes(5))
                .build();
        blogPostRepository.save(blogPost);
        return blogPost;
    }

    public List<BlogPost> findAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost findBlogPostById(long id) {
        return blogPostRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public void deleteBlogPost(long id) {
        blogPostRepository.deleteById(id);
    }




}

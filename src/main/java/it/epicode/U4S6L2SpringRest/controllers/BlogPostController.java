package it.epicode.U4S6L2SpringRest.controllers;

import it.epicode.U4S6L2SpringRest.entities.BlogPost;
import it.epicode.U4S6L2SpringRest.exceptions.NotFoundException;
import it.epicode.U4S6L2SpringRest.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogPosts")

public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    // GET /blogPosts => ritorna la lista di blog post

    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllBlogPosts() {
        List<BlogPost> blogPosts = blogPostService.getAllBlogPosts();
        ResponseEntity<List<BlogPost>> responseEntity = new ResponseEntity<>(blogPosts, HttpStatus.OK);
        return responseEntity;
    }

    // GET /blogPosts /123 => ritorna un singolo blog post

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable long id) throws NotFoundException {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        ResponseEntity<BlogPost> responseEntity = new ResponseEntity<>(blogPost, HttpStatus.OK);
        return responseEntity;
    }

    // POST /blogPosts => crea un nuovo blog post

    @PostMapping
    public ResponseEntity<BlogPost> saveBlogPost(@RequestBody BlogPost blogPost) {
        BlogPost savedBlogPost = blogPostService.saveBlogPost(blogPost);
        ResponseEntity<BlogPost> responseEntity = new ResponseEntity<>(savedBlogPost, HttpStatus.CREATED);
        return responseEntity;
    }

    // PUT /blogPosts /123 => modifica lo specifico blog post

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable long id, @RequestBody BlogPost updatedBlogPost) {
        BlogPost updatedPost = blogPostService.updateBlogPost(id, updatedBlogPost);
        ResponseEntity<BlogPost> responseEntity = new ResponseEntity<>(updatedPost, HttpStatus.OK);
        return responseEntity;
    }

    // DELETE /blogPosts /123 => cancella lo specifico blog post

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable long id) {
        blogPostService.deleteBlogPost(id);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return responseEntity;
    }
}

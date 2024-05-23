package it.epicode.U4S6L2SpringRest.controllers;

import it.epicode.U4S6L2SpringRest.dto.ExceptionPayloadDto;
import it.epicode.U4S6L2SpringRest.dto.ExceptionPayloadWithListDto;
import it.epicode.U4S6L2SpringRest.entities.BlogPost;
import it.epicode.U4S6L2SpringRest.entities.BlogPostAuthor;
import it.epicode.U4S6L2SpringRest.dto.NewBlogPostPayloadDto;
import it.epicode.U4S6L2SpringRest.exceptions.BadRequestException;
import it.epicode.U4S6L2SpringRest.exceptions.NoContentException;
import it.epicode.U4S6L2SpringRest.exceptions.NotFoundException;
import it.epicode.U4S6L2SpringRest.services.BlogPostAuthorService;
import it.epicode.U4S6L2SpringRest.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogPosts")

public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private BlogPostAuthorService blogPostAuthorService;

    // GET /blogPosts => ritorna la lista di blog post

    @GetMapping
    public ResponseEntity<Page<BlogPost>> getAllBlogPosts(Pageable pageable) {
        Page<BlogPost> blogPosts = (Page<BlogPost>) blogPostService.getAllBlogPosts(pageable);
        if (blogPosts.isEmpty()) {
            throw new NoContentException("The list of blog posts in empty.");
        } else {
            ResponseEntity<Page<BlogPost>> responseEntity = new ResponseEntity<>(blogPosts, HttpStatus.OK);
            return responseEntity;
        }
    }

    // GET /blogPosts /123 => ritorna un singolo blog post

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable long id) {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        if (blogPost == null) {
          throw new NotFoundException(id);
        } else {
            ResponseEntity<BlogPost> responseEntity = new ResponseEntity<>(blogPost, HttpStatus.OK);
            return responseEntity;
        }
    }

    // POST /blogPosts => crea un nuovo blog post

    @PostMapping
    public ResponseEntity<BlogPost> saveBlogPost(@RequestBody @Validated NewBlogPostPayloadDto blogPostPayload, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            BlogPost blogPost = BlogPost.builder()
                    .withCategory(blogPostPayload.category())
                    .withTitle(blogPostPayload.title())
                    .withCover(blogPostPayload.cover())
                    .withContent(blogPostPayload.content())
                    .withReadingTime(blogPostPayload.readingTime())
                    .build();
            BlogPostAuthor author = blogPostAuthorService.getBlogPostAuthorById(blogPostPayload.authorId());

            if (author == null) {
                throw new NotFoundException("Author with id " + blogPostPayload.authorId() + " not found.");
            } else {
                blogPost.setBlogPostAuthor(author);
            }
            BlogPost savedBlogPost = blogPostService.saveBlogPost(blogPost);
            ResponseEntity<BlogPost> responseEntity = new ResponseEntity<>(savedBlogPost, HttpStatus.CREATED);
            return responseEntity;
        }
    }

    // PUT /blogPosts /123 => modifica lo specifico blog post

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable long id, @RequestBody NewBlogPostPayloadDto updatedBlogPostPayload) {
        BlogPost searchedBlogPost = blogPostService.getBlogPostById(id);
        if (searchedBlogPost == null) {
            throw new NotFoundException("Blog post with id " + id + " not found.");
        }

        searchedBlogPost.setCategory(updatedBlogPostPayload.category());
        searchedBlogPost.setTitle(updatedBlogPostPayload.title());
        searchedBlogPost.setCover(updatedBlogPostPayload.cover());
        searchedBlogPost.setContent(updatedBlogPostPayload.content());
        searchedBlogPost.setReadingTime(updatedBlogPostPayload.readingTime());

        if (searchedBlogPost.getBlogPostAuthor().getId() != updatedBlogPostPayload.authorId()) {
            BlogPostAuthor author = blogPostAuthorService.getBlogPostAuthorById(updatedBlogPostPayload.authorId());
            if (author == null) {
                throw new NotFoundException("Author with id " + updatedBlogPostPayload.authorId() + " not found.");
            }
        }

        BlogPost updatedPost = blogPostService.updateBlogPost(id, updatedBlogPostPayload);

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

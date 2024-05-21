package it.epicode.U4S6L2SpringRest.controllers;

import it.epicode.U4S6L2SpringRest.entities.BlogPostAuthor;
import it.epicode.U4S6L2SpringRest.exceptions.NotFoundException;
import it.epicode.U4S6L2SpringRest.services.BlogPostAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class BlogPostAuthorController {

    @Autowired
    private BlogPostAuthorService blogPostAuthorService;

//    GET /authors => ritorna la lista di autori

    @GetMapping
    // @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BlogPostAuthor>> getAllBlogPostAuthors() {
        List<BlogPostAuthor> authors = blogPostAuthorService.getAllBlogPostAuthors();
        ResponseEntity<List<BlogPostAuthor>> responseEntity = new ResponseEntity<>(authors, HttpStatus.OK);
        return responseEntity;
    }

//    GET /authors/123 => ritorna un singolo autore

    @GetMapping("/{id}")
    // @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BlogPostAuthor> getBlogPostAuthorById(@PathVariable long id) throws NotFoundException {
        BlogPostAuthor author = blogPostAuthorService.getBlogPostAuthorById(id);
        ResponseEntity<BlogPostAuthor> responseEntity = new ResponseEntity<>(author, HttpStatus.OK);
        return responseEntity;
    }

//    POST /authors => crea un nuovo autore

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BlogPostAuthor> saveBlogPostAuthor(@RequestBody BlogPostAuthor blogPostAuthor) {
        BlogPostAuthor savedAuthor = blogPostAuthorService.saveBlogPostAuthor(blogPostAuthor);
        ResponseEntity<BlogPostAuthor> responseEntity = new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
        return responseEntity;
    }

//    PUT /authors/123 => modifica lo specifico autore

    @PutMapping("/{id}")
    // @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BlogPostAuthor> updateBlogPostAuthor(@PathVariable long id, @RequestBody BlogPostAuthor updatedBlogPostAuthor) {
        BlogPostAuthor updatedAuthor = blogPostAuthorService.updateBlogPostAuthor(id, updatedBlogPostAuthor);
        ResponseEntity<BlogPostAuthor> responseEntity = new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
        return responseEntity;
    }

//    DELETE /authors/123 => cancella lo specifico autore

    @DeleteMapping("/{id}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBlogPostAuthor(@PathVariable long id) {
        blogPostAuthorService.deleteBlogPostAuthor(id);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return responseEntity;
    }
}

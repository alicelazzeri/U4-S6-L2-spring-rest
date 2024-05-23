package it.epicode.U4S6L2SpringRest.services;

import it.epicode.U4S6L2SpringRest.entities.BlogPost;
import it.epicode.U4S6L2SpringRest.entities.BlogPostAuthor;
import it.epicode.U4S6L2SpringRest.dto.NewBlogPostPayloadDto;
import it.epicode.U4S6L2SpringRest.exceptions.NotFoundException;
import it.epicode.U4S6L2SpringRest.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private BlogPostAuthorService blogPostAuthorService;

    // GET all

    public Page<BlogPost> getAllBlogPosts(Pageable pageable) {
        return blogPostRepository.findAll(pageable);
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

    public BlogPost updateBlogPost(long id, NewBlogPostPayloadDto updatedBlogPostPayload) {
        var blogPost = blogPostRepository.findById(id).orElseThrow(() -> new NotFoundException("Blog post with id " + id + " not found."));

        blogPost.setCategory(updatedBlogPostPayload.category());
        blogPost.setTitle(updatedBlogPostPayload.title());
        blogPost.setCover(updatedBlogPostPayload.cover());
        blogPost.setContent(updatedBlogPostPayload.content());
        blogPost.setReadingTime(updatedBlogPostPayload.readingTime());

        if (blogPost.getBlogPostAuthor() == null || blogPost.getBlogPostAuthor().getId() != updatedBlogPostPayload.authorId()) {
            BlogPostAuthor author = blogPostAuthorService.getBlogPostAuthorById(updatedBlogPostPayload.authorId());
            if (author == null) {
                throw new NotFoundException("Author with id " + updatedBlogPostPayload.authorId() + " not found.");
            }
            blogPost.setBlogPostAuthor(author);
        }

        blogPostRepository.save(blogPost);
        return blogPost;
    }

    // DELETE

    public void deleteBlogPost(long id) {
        blogPostRepository.deleteById(id);
    }
}

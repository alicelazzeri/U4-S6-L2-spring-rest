package it.epicode.U4S6L2SpringRest.services;

import it.epicode.U4S6L2SpringRest.entities.BlogPost;
import it.epicode.U4S6L2SpringRest.entities.BlogPostAuthor;
import it.epicode.U4S6L2SpringRest.entities.NewBlogPostPayload;
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

    public BlogPost updateBlogPost(long id, NewBlogPostPayload updatedBlogPostPayload) {
        var blogPost = blogPostRepository.findById(id).orElseThrow(() -> new NotFoundException("Blog post with id " + id + " not found."));

        blogPost.setCategory(updatedBlogPostPayload.getCategory());
        blogPost.setTitle(updatedBlogPostPayload.getTitle());
        blogPost.setCover(updatedBlogPostPayload.getCover());
        blogPost.setContent(updatedBlogPostPayload.getContent());
        blogPost.setReadingTime(updatedBlogPostPayload.getReadingTime());

        if (blogPost.getBlogPostAuthor() == null || blogPost.getBlogPostAuthor().getId() != updatedBlogPostPayload.getAuthorId()) {
            BlogPostAuthor author = blogPostAuthorService.getBlogPostAuthorById(updatedBlogPostPayload.getAuthorId());
            if (author == null) {
                throw new NotFoundException("Author with id " + updatedBlogPostPayload.getAuthorId() + " not found.");
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

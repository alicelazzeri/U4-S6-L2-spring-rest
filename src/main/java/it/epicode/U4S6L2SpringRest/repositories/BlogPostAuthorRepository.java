package it.epicode.U4S6L2SpringRest.repositories;

import it.epicode.U4S6L2SpringRest.entities.BlogPostAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogPostAuthorRepository extends JpaRepository<BlogPostAuthor, Long>, PagingAndSortingRepository<BlogPostAuthor, Long> {
}

package it.epicode.U4S6L2SpringRest.repositories;

import it.epicode.U4S6L2SpringRest.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long>, PagingAndSortingRepository<BlogPost, Long> {
}

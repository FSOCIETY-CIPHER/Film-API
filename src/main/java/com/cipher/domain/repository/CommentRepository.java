package com.cipher.domain.repository;

import com.cipher.domain.model.Comment;
import com.cipher.domain.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByFilm(Film film, Pageable pageable);
}

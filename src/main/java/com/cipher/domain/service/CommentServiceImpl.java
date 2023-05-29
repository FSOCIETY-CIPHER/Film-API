package com.cipher.domain.service;

import com.cipher.application.dto.request.CommentRequest;
import com.cipher.application.dto.response.CommentResponse;
import com.cipher.application.dto.response.FilmResponse;
import com.cipher.domain.model.Comment;
import com.cipher.domain.model.Film;
import com.cipher.domain.repository.CommentRepository;
import com.cipher.domain.repository.FilmRepository;
import com.cipher.utils.PageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final FilmRepository filmRepository;
    @Transactional
    @Override
    public CommentResponse addComment(Long film_id, CommentRequest request) {
        Film film = filmRepository.findById(film_id).
                orElseThrow(()-> new RuntimeException("film with id: "+film_id+" not found"));
        Comment comment = commentRepository.save(Comment.builder()
                .body(request.getBody())
                .film(film)
                .commentDate(LocalDateTime.now())
                .build());
        List<Comment> comments = film.getComments();
        comments.add(comment);
        film.setComments(comments);
        film.setCommentCount(film.getCommentCount()+ 1);

        return CommentResponse.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .commentDate(comment.getCommentDate())
                .film_id(film.getId())
                .build();
    }

    @Override
    public PageUtils getComments(Integer pageNo, Integer pageSize, String commentDate, String sortDir, Long film_id) {
        Sort sort = sortDir.equalsIgnoreCase("ASC") ? Sort.by(commentDate).ascending()
                : Sort.by(commentDate).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Film film = filmRepository.findById(film_id).
                orElseThrow(()-> new RuntimeException("film with id: "+film_id+" not found"));
        Page<Comment> commentPage = commentRepository.findAllByFilm(film, pageable);
        System.out.println(commentPage.getContent().size());
        List<CommentResponse> commentResponses = commentPage.getContent().stream().map(comment ->
                CommentResponse.builder().id(comment.getId())
                        .commentDate(comment.getCommentDate())
                        .body(comment.getBody())
                        .film_id(film.getId()).build()).toList();
        return PageUtils.builder()
                .pageContent(commentResponses)
                .totalPages(commentPage.getTotalPages())
                .totalElements(commentPage.getTotalElements())
                .pageNo(commentPage.getNumber())
                .pageSize(commentPage.getSize())
                .last(commentPage.isLast())
                .build();
    }
}

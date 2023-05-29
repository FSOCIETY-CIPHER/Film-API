package com.cipher.domain.service;

import com.cipher.application.dto.request.CommentRequest;
import com.cipher.application.dto.response.CommentResponse;
import com.cipher.utils.PageUtils;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    CommentResponse addComment(Long film_id, CommentRequest request);

    PageUtils getComments(Integer pageNo, Integer pageSize, String releaseDate, String sortDir, Long film_id);
}

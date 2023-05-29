package com.cipher.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentResponse {
    private Long id;
    private String body;
    private LocalDateTime commentDate;
    private Long film_id;
}

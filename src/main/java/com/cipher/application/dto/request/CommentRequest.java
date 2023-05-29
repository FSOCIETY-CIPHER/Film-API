package com.cipher.application.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    @Size(min = 10, max = 500, message = "Comment must not be less than 10 characters & must not exceed 500 characters")
    @NotEmpty(message = "Comment must not be empty")
    private String body;
}

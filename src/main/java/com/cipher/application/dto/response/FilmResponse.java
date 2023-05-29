package com.cipher.application.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FilmResponse {
    private Long id;
    private String title;
    private Date releaseDate;
    private Long commentCount;
}

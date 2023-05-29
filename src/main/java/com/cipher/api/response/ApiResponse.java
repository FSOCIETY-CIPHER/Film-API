package com.cipher.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiResponse {
    private Integer count;
    private String next;
    private String previous;
    private List<ApiFilmResponse> results;
}

package com.cipher.domain.service;

import com.cipher.api.connection.ApiConnection;
import com.cipher.api.connection.ConnectionString;
import com.cipher.api.response.ApiResponse;
import com.cipher.api.response.ApiFilmResponse;
import com.cipher.application.dto.response.FilmResponse;
import com.cipher.domain.model.Film;
import com.cipher.domain.repository.FilmRepository;
import com.cipher.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService, ApplicationRunner {
    private final FilmRepository filmRepository;
    private final ApiConnection apiConnection;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ApiResponse apiResponse = apiConnection.connectAndGet(ConnectionString.FILM_LINK,
                ApiResponse.class);
        List<ApiFilmResponse> filmResponses = apiResponse.getResults();
        for(ApiFilmResponse apiFilmResponse : filmResponses){
            filmRepository.save(Film.builder()
                            .commentCount(0L)
                            .title(apiFilmResponse.getTitle())
                            .releaseDate(apiFilmResponse.getRelease_date())
                    .build());
        }
        System.out.println(filmResponses);
    }

    @Override
    public PageUtils getFilms(Integer pageNo, Integer pageSize, String releaseDate, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("ASC") ? Sort.by(releaseDate).ascending()
                : Sort.by(releaseDate).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Film> filmPage = filmRepository.findAll(pageable);
        List<FilmResponse> filmResponses = filmPage.getContent().stream().map(film ->
                FilmResponse.builder().id(film.getId())
                        .releaseDate(film.getReleaseDate())
                        .title(film.getTitle())
                        .commentCount(film.getCommentCount()).build()).toList();
        return PageUtils.builder()
                .pageContent(filmResponses)
                .totalPages(filmPage.getTotalPages())
                .totalElements(filmPage.getTotalElements())
                .pageNo(filmPage.getNumber())
                .pageSize(filmPage.getSize())
                .last(filmPage.isLast())
                .build();
    }
}

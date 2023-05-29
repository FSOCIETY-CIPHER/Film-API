package com.cipher.application.controller;


import com.cipher.application.dto.response.Response;
import com.cipher.domain.service.FilmService;
import com.cipher.utils.PageConstants;
import com.cipher.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @GetMapping("/")
    public ResponseEntity<Response<PageUtils>> getAllFilms(
            @RequestParam(value = "pageNo", defaultValue = PageConstants.page_no,required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageConstants.page_size,required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageConstants.sort_by_film,required = false) String releaseDate,
            @RequestParam(value = "sortDir", defaultValue = PageConstants.sort_direction,required = false) String sortDir
            ){
        Response<PageUtils> response = new Response<>(filmService.getFilms(pageNo,pageSize,releaseDate,sortDir));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

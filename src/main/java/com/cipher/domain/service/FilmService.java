package com.cipher.domain.service;

import com.cipher.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface FilmService {
    PageUtils getFilms(Integer pageNo, Integer pageSize, String releaseDate, String sortDir);
}

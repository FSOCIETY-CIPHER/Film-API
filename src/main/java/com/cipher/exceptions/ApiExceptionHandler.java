package com.cipher.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;


@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(FilmNotFoundException.class)

    public ResponseEntity<ExceptionResponse> filmNotFound(FilmNotFoundException e, HttpServletRequest request){

        ExceptionResponse response = ExceptionResponse.builder()

                .message(e.getMessage())

                .statusCode(HttpStatus.NOT_FOUND.value())

                .path(request.getRequestURI())

                .time(LocalDateTime.now())

                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

}






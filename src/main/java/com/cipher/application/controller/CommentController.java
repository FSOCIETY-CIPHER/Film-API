package com.cipher.application.controller;

import com.cipher.application.dto.request.CommentRequest;
import com.cipher.application.dto.response.CommentResponse;
import com.cipher.application.dto.response.Response;
import com.cipher.domain.service.CommentService;
import com.cipher.utils.PageConstants;
import com.cipher.utils.PageUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{film_Id}")
    public ResponseEntity<Response<CommentResponse>> addComment(@PathVariable Long film_Id,
                                                                @Valid @RequestBody CommentRequest request){
        Response<CommentResponse> response = new Response<>(commentService.addComment(film_Id,request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{film_id}")
    public ResponseEntity<Response<PageUtils>> getComments(
            @RequestParam(value = "pageNo", defaultValue = PageConstants.page_no,required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageConstants.page_size,required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageConstants.sort_by_comment,required = false) String commentDate,
            @RequestParam(value = "sortDir", defaultValue = PageConstants.sort_direction,required = false) String sortDir,
            @PathVariable Long film_id){
        Response<PageUtils> response = new Response<>(commentService.getComments(pageNo,pageSize,commentDate,sortDir,film_id));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}

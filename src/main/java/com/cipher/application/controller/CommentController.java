package com.cipher.application.controller;

import com.cipher.application.dto.request.CommentRequest;
import com.cipher.application.dto.response.CommentResponse;
import com.cipher.application.dto.response.Response;
import com.cipher.domain.service.CommentService;
import com.cipher.utils.PageConstants;
import com.cipher.utils.PageUtils;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Api(tags = "Comment API")
public class CommentController {
    private final CommentService commentService;


    @ApiOperation(value = "Add a comment to a film",
            notes = "Adds a comment to the specified film",
            response = Response.class)
    @PostMapping("/{film_Id}")
    public ResponseEntity<Response<CommentResponse>> addComment(@PathVariable Long film_Id,
                                                                @Valid @RequestBody CommentRequest request){
        Response<CommentResponse> response = new Response<>(commentService.addComment(film_Id,request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get Comments",
            notes = "Retrieves comments for a specific film",
            response = Response.class,
            responseContainer = "ResponseEntity")
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

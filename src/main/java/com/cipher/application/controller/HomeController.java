package com.cipher.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/home")
@Api(tags = "Home API")
public class HomeController {
    @ApiOperation(value = "Check deployment status",
            notes = "Returns the status of the deployment",
            response = String.class)
    @GetMapping
    public String deployStatus(){
        return "Deployed Successfully";
    }
}

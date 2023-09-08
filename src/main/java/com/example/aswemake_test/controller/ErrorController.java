package com.example.aswemake_test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/error/")
@RequiredArgsConstructor
@Slf4j
public class ErrorController {

    @GetMapping("/authorization")
    public String authorizationError(){
        return "권한이 없습니다.!";
    }
}

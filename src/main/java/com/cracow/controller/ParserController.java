package com.cracow.controller;

import com.cracow.service.ParserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Parser API")
@RestController
@RequestMapping("/api/parser")
public class ParserController {

    private final ParserService parserService;

    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }

    @GetMapping
    public void settingBlob(@RequestParam String id) throws Exception {
        parserService.parseImg(id);
    }
}

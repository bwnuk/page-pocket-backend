package com.cracow.controllers;

import com.cracow.services.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/parser")
public class ParserController {

    @Autowired
    ParserService parserService;

    @GetMapping
    public void settingBlob(@RequestParam String id) throws Exception {
        parserService.parseImg(id);
    }
}

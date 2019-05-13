package com.cracow.controllers;

import com.cracow.services.ParserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parser")
@Api(value="web-page to picture parser", description="parsing sites to some picture format")
public class ParserController {

    @Autowired
    ParserService parserService;

    @GetMapping
    public void settingBlob(@RequestParam String id) throws Exception {
        parserService.parseImg(id);
    }

//    @GetMapping("/doNothing")
//    public ResponseEntity<Void> doNothing()
//    {
//        return new ResponseEntity<Void>(HttpStatus.OK);
//    }
}

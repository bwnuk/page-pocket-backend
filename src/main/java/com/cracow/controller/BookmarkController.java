package com.cracow.controller;

import com.cracow.dto.input.BookmarkNewDto;
import com.cracow.dto.output.BookmarkBlobDto;
import com.cracow.dto.output.BookmarkDto;
import com.cracow.service.BookmarkService;
import com.cracow.service.security.SecurityService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Api(value = "Bookmarks API")
@Validated
@RestController
@RequestMapping("/api/pockets")
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final SecurityService securityService;

    public BookmarkController(BookmarkService bookmarkService, SecurityService securityService) {
        this.bookmarkService = bookmarkService;
        this.securityService = securityService;
    }

    @PreAuthorize("isFullyAuthenticated()")
    @PostMapping
    public ResponseEntity<BookmarkDto> saveBookmark(
            @RequestBody @Valid BookmarkNewDto bookmarkNewDto,
            @RequestParam(required = false, defaultValue = "false") boolean parse) {
        BookmarkDto result = bookmarkService.saveBookmark(bookmarkNewDto, parse);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PreAuthorize("isFullyAuthenticated()")
    @GetMapping
    public ResponseEntity<List<BookmarkDto>> findAll(@RequestParam(required = false) Optional<String> tag) {
        List<BookmarkDto> result = bookmarkService.findAll(tag);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("isFullyAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<BookmarkBlobDto> findById(@PathVariable String id) {
        BookmarkBlobDto result = bookmarkService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("isFullyAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        bookmarkService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("isFullyAuthenticated()")
    @PostMapping("/{id}")
    public ResponseEntity<BookmarkBlobDto> parseLink(@PathVariable String id) {
        BookmarkBlobDto result = bookmarkService.parseLink(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

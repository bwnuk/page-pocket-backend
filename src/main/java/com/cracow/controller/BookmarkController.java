package com.cracow.controller;

import com.cracow.dto.input.BookmarkNewDto;
import com.cracow.dto.output.BookmarkBlobDto;
import com.cracow.dto.output.BookmarkDto;
import com.cracow.service.BookmarkService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Bookmarks API")
@Validated
@RestController
@RequestMapping("/api/pockets")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/email/{email}")
    public ResponseEntity<BookmarkDto> saveBookmark(
            @PathVariable String email,
            @RequestBody @Valid BookmarkNewDto bookmarkNewDto) {
        BookmarkDto result = bookmarkService.saveBookmarkByEmail(email, bookmarkNewDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PreAuthorize("isFullyAuthenticated() and #email == authentication.name")
    @GetMapping("/email/{email}")
    public ResponseEntity<List<BookmarkDto>> findAllByEmail(@PathVariable String email) {
        List<BookmarkDto> result = bookmarkService.findAllByEmail(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("isFullyAuthenticated() and #email == authentication.name")
    @GetMapping("/{id}/email/{email}")
    public ResponseEntity<BookmarkBlobDto> findByIdForEmail(@PathVariable String id, @PathVariable String email) {
        BookmarkBlobDto result = bookmarkService.findByIdForEmail(id, email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("isFullyAuthenticated() and #email == authentication.name")
    @DeleteMapping("/{id}/email/{email}")
    public ResponseEntity<Void> deleteById(@PathVariable String id, @PathVariable String email) {
        bookmarkService.deleteByIdForEmail(id, email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

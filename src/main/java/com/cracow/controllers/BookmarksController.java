package com.cracow.controllers;

import com.cracow.dto.BookmarkNewDto;
import com.cracow.dto.BookmarksDTO;
import com.cracow.dto.UserDto;
import com.cracow.entities.Bookmarks;

import com.cracow.entities.User;
import com.cracow.repositories.BookmarkRepository;
import com.cracow.services.BookmarksService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/pockets")
public class BookmarksController {

    @Autowired
    private BookmarksService bookmarksService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<BookmarksDTO> saveBookmarks(@RequestBody BookmarkNewDto bookmarkNewDto) {
        try {
            Bookmarks bookE = convertBToEntity(bookmarkNewDto);
            if (bookmarksService.saveBook(bookE)) {
                BookmarksDTO bookD = bookmarksConvertToDTO(bookE);
                return new ResponseEntity<BookmarksDTO>(bookD,HttpStatus.CREATED);
            }
            else
                return new ResponseEntity<BookmarksDTO>(HttpStatus.CONFLICT);


        }

        catch (Exception e)
        {
            return new ResponseEntity<BookmarksDTO>(HttpStatus.CONFLICT);
        }
    }

    private BookmarkNewDto bookmarksConvertToNewDTO(Bookmarks bookmarks)
    {
        BookmarkNewDto newBookmarks = modelMapper.map(bookmarks, BookmarkNewDto.class);
        return newBookmarks;
    }

    private Bookmarks convertBToEntity(BookmarkNewDto bookmarkNewDto) throws ParseException {
        Bookmarks bookmarks = modelMapper.map(bookmarkNewDto, Bookmarks.class);
        return bookmarks;
    }

    private BookmarksDTO bookmarksConvertToDTO(Bookmarks bookmarks)
    {
        BookmarksDTO newBookmarks = modelMapper.map(bookmarks, BookmarksDTO.class);
        return newBookmarks;
    }



}

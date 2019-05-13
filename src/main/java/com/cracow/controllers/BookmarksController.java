package com.cracow.controllers;

import com.cracow.dto.BookmarkBlobDTO;
import com.cracow.dto.BookmarkNewDto;
import com.cracow.dto.BookmarksDTO;
import com.cracow.entities.Bookmarks;
import com.cracow.services.BookmarksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pockets")
@Api(value="bookmarks", description="operations pertaining to bookmarks")
public class BookmarksController {

    @Autowired
    private BookmarksService bookmarksService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ApiOperation(value="save bookmark", response=ResponseEntity.class)
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

    @GetMapping
    @ApiOperation(value="get all bookmarks", response=List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved bookmarks list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<List<BookmarksDTO>> getAll()
    {
        List<BookmarksDTO> result = new ArrayList<>();
        for (Bookmarks book : bookmarksService.getAllBookmarks())
        {
            result.add(bookmarksConvertToDTO(book));
        }

        return new ResponseEntity<List<BookmarksDTO>>(result,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value="find bookmarn by ID", response=BookmarkBlobDTO.class)
    public ResponseEntity<BookmarkBlobDTO> findById(@PathVariable String id)
    {
        BookmarkBlobDTO result = bookmarkBlobConvertToDTO(bookmarksService.findById(id).get());
        if (result != null) {
            return new ResponseEntity<BookmarkBlobDTO>(result, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<BookmarkBlobDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="delete bookmark by ID")
    public ResponseEntity<Void> deleteById(@PathVariable String id)
    {
        if (findById(id) != null) {
            bookmarksService.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
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

    private BookmarkBlobDTO bookmarkBlobConvertToDTO(Bookmarks bookmarks)
    {
        BookmarkBlobDTO newBookmarks = modelMapper.map(bookmarks, BookmarkBlobDTO.class);
        return newBookmarks;
    }





}
